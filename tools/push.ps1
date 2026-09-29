<#
.SYNOPSIS
    带自动重试的 git push。

.DESCRIPTION
    访问 github.com 的连接经常间歇性失败，git push 会因为 TCP 连不上直接报错退出，
    每次还要白等约 21 秒超时。本脚本先做快速 TCP 探活（3 秒超时），端口通了才真正
    推送，不通就等待重试，直到成功或用完次数。

    编码说明：本 .ps1 必须保存为 UTF-8 带 BOM。Windows PowerShell 5.1 读无 BOM 的
    脚本会按 GBK 解码，中文乱码会破坏引号配对，直接语法报错。

.EXAMPLE
    .\tools\push.ps1

.EXAMPLE
    .\tools\push.ps1 -MaxAttempts 60 -IntervalSeconds 20
#>
[CmdletBinding()]
param(
    [int]$MaxAttempts = 40,
    [int]$IntervalSeconds = 15,
    [int]$ProbeTimeoutMs = 3000,
    [string]$Remote = 'origin',
    [string]$Branch = ''
)

$ErrorActionPreference = 'Continue'
$repoRoot = Split-Path -Parent $PSScriptRoot

function Test-TcpFast {
    param([string]$TargetHost, [int]$Port, [int]$TimeoutMs)
    $client = New-Object System.Net.Sockets.TcpClient
    try {
        $iar = $client.BeginConnect($TargetHost, $Port, $null, $null)
        if (-not $iar.AsyncWaitHandle.WaitOne($TimeoutMs, $false)) { return $false }
        $client.EndConnect($iar)
        return $true
    }
    catch { return $false }
    finally { try { $client.Close() } catch { } }
}

function Test-RemoteRef {
    param([string]$Root, [string]$RemoteName, [string]$BranchName, [string]$ExpectSha, [int]$Tries = 3)
    for ($k = 1; $k -le $Tries; $k++) {
        $line = git -C $Root ls-remote $RemoteName "refs/heads/$BranchName" 2>$null
        if ($LASTEXITCODE -eq 0 -and $line) {
            $sha = ($line -split '\s+')[0].Trim()
            if ($sha -eq $ExpectSha) { return 'match' }
            return 'mismatch'
        }
        Start-Sleep -Seconds 5
    }
    return 'unknown'
}

if ([string]::IsNullOrWhiteSpace($Branch)) {
    $Branch = (git -C $repoRoot rev-parse --abbrev-ref HEAD).Trim()
}

Write-Host "推送 $Remote/$Branch  最多 $MaxAttempts 次，间隔 $IntervalSeconds 秒" -ForegroundColor Cyan

for ($i = 1; $i -le $MaxAttempts; $i++) {
    $ts = Get-Date -Format 'HH:mm:ss'

    if (-not (Test-TcpFast -TargetHost 'github.com' -Port 443 -TimeoutMs $ProbeTimeoutMs)) {
        Write-Host "[$ts] $i/$MaxAttempts  github.com:443 不通，等待 ${IntervalSeconds}s" -ForegroundColor DarkYellow
        Start-Sleep -Seconds $IntervalSeconds
        continue
    }

    Write-Host "[$ts] $i/$MaxAttempts  端口通，尝试推送" -ForegroundColor Gray
    $out = git -C $repoRoot push $Remote $Branch 2>&1
    $code = $LASTEXITCODE
    $joined = ($out -join "`n")

    # 输出里带 error: / fatal: 时，即使末尾出现「Everything up-to-date」也不能算成功。
    # 实测踩过（血泪）：连接中途断开时 git 会打出
    #     error: RPC failed; curl 52 Empty reply from server
    #     fatal: the remote end hung up unexpectedly
    #     Everything up-to-date
    # 退出码非 0，而远端其实还是旧提交（用 ls-remote 核对过）。所以必须要求没有错误行。
    $hasErrorLine = $joined -match '(?m)^\s*(error|fatal):'
    $looksOk = ($code -eq 0) -or (($joined -match 'Everything up-to-date') -and (-not $hasErrorLine))

    if ($looksOk) {
        $out | ForEach-Object { Write-Host "  $_" }
        $head = (git -C $repoRoot rev-parse HEAD).Trim()
        $verdict = Test-RemoteRef -Root $repoRoot -RemoteName $Remote -BranchName $Branch -ExpectSha $head -Tries 3

        if ($verdict -eq 'mismatch') {
            Write-Host "  远端 ref 仍是旧的，本次推送未真正生效，继续重试" -ForegroundColor DarkYellow
            Start-Sleep -Seconds $IntervalSeconds
            continue
        }
        if ($verdict -eq 'match') {
            Write-Host "推送成功 ✅  已核对远端 $Remote/$Branch = $head" -ForegroundColor Green
            exit 0
        }
        Write-Host "推送成功 ✅，但没能连上远端做独立核对（本地提交是安全的）" -ForegroundColor Yellow
        exit 0
    }

    $last = ($out | Select-Object -Last 1)
    Write-Host "  推送失败：$last" -ForegroundColor DarkYellow
    Start-Sleep -Seconds $IntervalSeconds
}

Write-Host "用完 $MaxAttempts 次仍未成功。代码已在本地提交，联网后重跑本脚本即可。" -ForegroundColor Red
exit 1

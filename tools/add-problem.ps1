<#
.SYNOPSIS
    把一段 LeetCode 题解代码收录进本仓库。

.DESCRIPTION
    自动完成：生成唯一类名 -> 建目录 -> 去 package 声明 -> 改类名 ->
    写文件 -> 在 README 索引表插入一行（按题号排序）-> 可选提交。

    编码说明（踩过坑，别乱改）：
      * 本 .ps1 文件必须保存为「UTF-8 带 BOM」。Windows PowerShell 5.1 读无 BOM 的
        脚本会按 GBK 解码，中文乱码会破坏引号配对，直接语法报错。
      * 写出的 .java / .md 必须是「UTF-8 无 BOM」。javac 遇到 BOM 会报
        「非法字符: '\ufeff'」并编译失败。
      所以文件读写一律走 [IO.File] 显式编码，不用 Set-Content -Encoding utf8
      （它在 PS 5.1 下会加 BOM，在 PS 7 下不加，行为不一致）。

.EXAMPLE
    .\tools\add-problem.ps1 -Number 2 -Title "Add Two Numbers" -Topic linkedlist -Difficulty Medium -Note "哑结点 + 进位" -FromClipboard -Commit

.EXAMPLE
    .\tools\add-problem.ps1 -Number 121 -Title "Best Time to Buy and Sell Stock" -Topic dp -Source .\scratch\Solution.java

.EXAMPLE
    # Title 里没有英文字母时，用 -Slug 指定拼类名用的英文
    .\tools\add-problem.ps1 -Number 1 -Title "两数之和" -Slug TwoSum -Topic array -FromClipboard
#>
[CmdletBinding(DefaultParameterSetName = 'FromFile')]
param(
    [Parameter(Mandatory = $true)]
    [int]$Number,

    [Parameter(Mandatory = $true)]
    [string]$Title,

    [string]$Slug,
    [string]$Topic = 'misc',

    [ValidateSet('Easy', 'Medium', 'Hard', '')]
    [string]$Difficulty = '',

    [string]$Note = '',

    [Parameter(ParameterSetName = 'FromFile', Mandatory = $true)]
    [string]$Source,

    [Parameter(ParameterSetName = 'FromClipboard', Mandatory = $true)]
    [switch]$FromClipboard,

    [switch]$Commit
)

$ErrorActionPreference = 'Stop'

$script:Utf8NoBom = New-Object System.Text.UTF8Encoding($false)
$repoRoot = Split-Path -Parent $PSScriptRoot
$readmePath = Join-Path $repoRoot 'README.md'

function Read-Utf8Text {
    param([string]$Path)
    $text = [IO.File]::ReadAllText($Path, [Text.Encoding]::UTF8)
    return $text.TrimStart([char]0xFEFF)
}

function Write-Utf8Text {
    param([string]$Path, [string]$Text)
    [IO.File]::WriteAllText($Path, $Text, $script:Utf8NoBom)
}

function Read-Utf8Lines {
    param([string]$Path)
    return @([IO.File]::ReadAllLines($Path, [Text.Encoding]::UTF8))
}

function Write-Utf8Lines {
    param([string]$Path, [string[]]$Lines)
    [IO.File]::WriteAllLines($Path, $Lines, $script:Utf8NoBom)
}

function ConvertTo-PascalCase {
    param([string]$Text)
    # 只保留纯 ASCII 字母数字片段，避免中文混进类名
    $parts = @([regex]::Split($Text, '[^A-Za-z0-9]+') | Where-Object { $_ -match '^[A-Za-z0-9]+$' })
    if ($parts.Count -eq 0) { return '' }
    $out = foreach ($p in $parts) {
        $rest = $p.Substring(1)
        if ($rest -cmatch '^[A-Z0-9]+$' -and $rest -cmatch '[A-Z]') { $rest = $rest.ToLower() }
        $p.Substring(0, 1).ToUpper() + $rest
    }
    return ($out -join '')
}

# ---------- 1. 算出类名和路径 ----------
if ([string]::IsNullOrWhiteSpace($Slug)) {
    # Title 一般写成「English / 中文」，这里只取 / 之前的部分来推类名。
    # 否则中文里夹带的单个拉丁字母会被当成一个独立词拼到类名尾部 —— 实测
    # 「Remove Nth Node From End of List / 删除链表的倒数第 N 个结点」
    # 会推出 ...RemoveNthNodeFromEndOfListN，凭空多一个 N。
    $Slug = ConvertTo-PascalCase (($Title -split '/')[0])
    if ([string]::IsNullOrWhiteSpace($Slug)) { $Slug = ConvertTo-PascalCase $Title }
    if ([string]::IsNullOrWhiteSpace($Slug)) {
        throw "Title「$Title」里没有英文字母，无法推导类名。请补一个 -Slug，例如 -Slug TwoSum"
    }
}
else {
    $Slug = ConvertTo-PascalCase $Slug
    if ([string]::IsNullOrWhiteSpace($Slug)) {
        throw "-Slug「$Slug」里没有可用的英文字母。"
    }
}

$className = 'P{0:D4}{1}' -f $Number, $Slug
$relPath = "src/leetcode/$Topic/$className.java"
$destPath = Join-Path $repoRoot ($relPath -replace '/', [IO.Path]::DirectorySeparatorChar)

if (Test-Path -LiteralPath $destPath) {
    throw "$relPath 已存在。换个题号，或先手动删掉它。"
}

# ---------- 2. 取代码 ----------
if ($PSCmdlet.ParameterSetName -eq 'FromClipboard') {
    $code = Get-Clipboard -Raw
    if ([string]::IsNullOrWhiteSpace($code)) { throw "剪贴板是空的，先把 LeetCode 代码复制一下。" }
    $origin = '剪贴板'
}
else {
    if (-not (Test-Path -LiteralPath $Source)) { throw "找不到源文件：$Source" }
    $code = Read-Utf8Text -Path $Source
    if ([string]::IsNullOrWhiteSpace($code)) { throw "源文件是空的：$Source" }
    $origin = $Source
}

# ---------- 3. 去掉 package 声明 ----------
$strippedPackage = $false
$newCode = [regex]::Replace($code, '(?m)^\s*package\s+[\w\.]+\s*;\s*\r?\n', '')
if ($newCode -ne $code) { $strippedPackage = $true; $code = $newCode }

# ---------- 4. 把 Solution 改成唯一类名 ----------
$renamed = $false
$newCode = [regex]::Replace($code, '\bclass\s+Solution\b', "class $className")
if ($newCode -ne $code) { $renamed = $true; $code = $newCode }

$existingClasses = @([regex]::Matches($code, '(?m)^\s*(?:public\s+|final\s+|abstract\s+)*class\s+(\w+)') |
    ForEach-Object { $_.Groups[1].Value })

if (-not $renamed) {
    Write-Warning "代码里没找到「class Solution」，类名保持原样。"
    if ($existingClasses.Count -gt 0 -and $existingClasses -notcontains $className) {
        Write-Warning "注意：顶层类名是 $($existingClasses -join ', ')，不是 $className —— 若和别的题重名，整包编译会报「重复」。"
    }
}

# ---------- 4.5 自动补 Java 导入 ----------
# LeetCode 的编辑器自带 java.util 等导入，但复制出来的代码没有 import 语句，
# 直接存成文件会因为「找不到符号」编译失败。这里按代码里出现的类型名补通配导入。
# 如果代码里本来就有 import，就完全不动它。
$addedImports = @()
$hasImport = [regex]::IsMatch($code, '(?m)^\s*import\s+')

if (-not $hasImport) {
    $groups = [ordered]@{
        'java.util.*'          = @(
            'List', 'ArrayList', 'LinkedList', 'Map', 'HashMap', 'LinkedHashMap',
            'Set', 'HashSet', 'LinkedHashSet', 'TreeMap', 'TreeSet', 'SortedMap', 'SortedSet',
            'Queue', 'Deque', 'ArrayDeque', 'Stack', 'PriorityQueue', 'Vector',
            'Arrays', 'Collections', 'Iterator', 'ListIterator', 'Comparator',
            'Objects', 'Optional', 'StringJoiner', 'Random', 'BitSet'
        )
        'java.util.stream.*'   = @('Stream', 'IntStream', 'LongStream', 'DoubleStream', 'Collectors')
        'java.util.function.*' = @(
            'Function', 'BiFunction', 'Supplier', 'Consumer', 'Predicate',
            'UnaryOperator', 'BinaryOperator', 'IntFunction', 'ToIntFunction', 'IntPredicate'
        )
    }

    $prefix = ''
    foreach ($ns in $groups.Keys) {
        $hit = $false
        foreach ($t in $groups[$ns]) {
            if ([regex]::IsMatch($code, "\b$t\b")) { $hit = $true; break }
        }
        if ($hit) {
            $prefix += "import $ns;`r`n"
            $addedImports += $ns
        }
    }

    if ($prefix) { $code = $prefix + "`r`n" + $code }
}

# ---------- 5. 写文件 ----------
$destDir = Split-Path -Parent $destPath
if (-not (Test-Path -LiteralPath $destDir)) {
    New-Item -ItemType Directory -Path $destDir -Force | Out-Null
}
Write-Utf8Text -Path $destPath -Text $code.TrimEnd()

# ---------- 6. 更新 README 索引 ----------
$row = "| $Number | $Title | $Difficulty | $Topic | $Note | [$className.java]($relPath) |"
$indexUpdated = $false

if (Test-Path -LiteralPath $readmePath) {
    $lines = Read-Utf8Lines -Path $readmePath
    $startIdx = [array]::IndexOf($lines, '<!-- INDEX:START -->')
    $endIdx = [array]::IndexOf($lines, '<!-- INDEX:END -->')

    if ($startIdx -ge 0 -and $endIdx -gt $startIdx) {
        $result = New-Object System.Collections.Generic.List[string]

        if ($startIdx -gt 0) { $result.AddRange([string[]]$lines[0..($startIdx - 1)]) }
        $result.Add($lines[$startIdx])
        $result.Add($lines[$startIdx + 1])   # 表头
        $result.Add($lines[$startIdx + 2])   # 分隔线

        $rows = New-Object System.Collections.Generic.List[string]
        $rowStart = $startIdx + 3
        $rowEnd = $endIdx - 1
        if ($rowEnd -ge $rowStart) {
            foreach ($l in $lines[$rowStart..$rowEnd]) {
                if ($l -match '^\s*\|') { $rows.Add($l) }
            }
        }
        $rows.Add($row)

        $sorted = $rows | Sort-Object -Property @{ Expression = {
                    $cell = ($_ -split '\|')[1]
                    $n = 0
                    if ([int]::TryParse($cell.Trim(), [ref]$n)) { $n } else { [int]::MaxValue }
                } }
        $result.AddRange([string[]]$sorted)

        $result.AddRange([string[]]$lines[$endIdx..($lines.Count - 1)])

        Write-Utf8Lines -Path $readmePath -Lines $result.ToArray()
        $indexUpdated = $true
    }
    else {
        Write-Warning "README.md 里没找到 <!-- INDEX:START --> / <!-- INDEX:END --> 标记，索引没更新。"
    }
}
else {
    Write-Warning "没找到 README.md，索引没更新。"
}

# ---------- 7. 汇总 ----------
Write-Host ""
Write-Host "收录完成" -ForegroundColor Green
Write-Host "  类名     : $className"
Write-Host "  文件     : $relPath"
Write-Host "  来源     : $origin"
Write-Host "  改类名   : $(if ($renamed) { "是（Solution -> $className）" } else { '否' })"
Write-Host "  去package: $(if ($strippedPackage) { '是' } else { '否' })"
Write-Host "  补import : $(if ($addedImports.Count -gt 0) { '是（' + ($addedImports -join ', ') + '）' } else { '否' })"
Write-Host "  索引     : $(if ($indexUpdated) { '已更新' } else { '未更新' })"

if ($Commit) {
    git -C $repoRoot add -- $relPath README.md
    $msg = "新增 ${className}: $Title" + $(if ($Difficulty) { " ($Difficulty)" } else { '' })
    git -C $repoRoot commit -m $msg
    if ($LASTEXITCODE -eq 0) { Write-Host "已提交：$msg" -ForegroundColor Green }
    else { Write-Warning "git commit 失败，请手动检查。" }
}
else {
    Write-Host ""
    Write-Host "还没提交。检查一下代码，然后：" -ForegroundColor Yellow
    Write-Host "  git add . ; git commit -m `"新增 ${className}: $Title`""
}

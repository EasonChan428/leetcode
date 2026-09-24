# leetcode-java

LeetCode 题解（Java）。

## 约定

- **一题一文件**，类名唯一：`P` + 四位题号 + 驼峰题名，例如 `P0001TwoSum`。
- **不写 `package` 声明**，文件按主题放在 `src/leetcode/<主题>/` 下。
- 之所以每题都用唯一类名，是因为 Java 的默认包只有一个，多个叫 `Solution` 的类放在一起会编译报错「重复: Solution」。唯一类名让整个 `src` 可以一次性编译通过。

## 目录结构

```
leetcode-java/
├── README.md                     # 本文件，题目索引
├── .gitignore                    # 忽略 bin/ 等编译产物
├── .vscode/settings.json         # VS Code Java 工程配置
├── tools/
│   └── add-problem.ps1           # 收录新题解的脚本
└── src/
    └── leetcode/
        └── array/
            └── P0001TwoSum.java
```

## 题目索引

<!-- INDEX:START -->
| 题号 | 题目 | 难度 | 主题 | 解法要点 | 文件 |
| ---: | --- | :--: | --- | --- | --- |
| 1 | Two Sum / 两数之和 | Easy | array | 暴力双重循环，O(n²) | [P0001TwoSum.java](src/leetcode/array/P0001TwoSum.java) |
| 49 | Group Anagrams / 字母异位词分组 | Medium | hash | 排序后的字符串作哈希键，O(n·k log k) | [P0049GroupAnagrams.java](src/leetcode/hash/P0049GroupAnagrams.java) |
<!-- INDEX:END -->

## 怎么加一道新题

在 VS Code 里把 LeetCode 的代码复制到剪贴板，然后执行：

```powershell
# 从剪贴板收录
.\tools\add-problem.ps1 -Number 2 -Title "Add Two Numbers" -Topic linkedlist -Difficulty Medium -Note "哑结点 + 进位" -FromClipboard -Commit

# 从已有的 .java 文件收录
.\tools\add-problem.ps1 -Number 121 -Title "Best Time to Buy and Sell Stock" -Topic dp -Difficulty Easy -Source .\scratch\Solution.java
```

脚本会自动完成这些事：

1. 生成唯一的类名（`P0002AddTwoNumbers`）并把代码里的 `class Solution` 替换掉；
2. 把文件写到 `src/leetcode/<主题>/` 下；
3. 删掉代码里多余的 `package` 声明；
4. **自动补 import**：LeetCode 编辑器自带 `java.util` 等导入，复制出来的代码没有
   `import` 语句，直接存盘会因「找不到符号」编译失败。脚本会扫描代码里用到的类型名
   （`List` / `Map` / `HashMap` / `Arrays` / `Collectors` …），按需补上
   `import java.util.*;`、`java.util.stream.*`、`java.util.function.*`。
   如果代码里本来就有 `import`，则一个字都不动；
5. 在本文的索引表里插入一行（按题号排序）；
6. 加了 `-Commit` 就顺手 `git add` + `git commit`。

参数说明：

| 参数 | 必填 | 说明 |
| --- | :--: | --- |
| `-Number` | 是 | LeetCode 题号，例如 `1` |
| `-Title` | 是 | 题目名，可中英文混写，例如 `Two Sum / 两数之和` |
| `-Slug` | 否 | 用于拼类名的英文名。`-Title` 里没有英文字母时必须给，例如 `-Slug TwoSum` |
| `-Topic` | 否 | 主题子目录名，默认 `misc` |
| `-Difficulty` | 否 | `Easy` / `Medium` / `Hard` |
| `-Note` | 否 | 一句话解法要点 |
| `-Source` | 二选一 | 源 `.java` 文件路径 |
| `-FromClipboard` | 二选一 | 直接读剪贴板 |
| `-Commit` | 否 | 收录后自动提交 |

## 环境

- JDK：javac 24.0.2
- Shell：Windows PowerShell 5.1（**没有** pwsh 7）
- VS Code 配置见 `.vscode/settings.json`：源码根目录 `src`，编译输出 `bin`（已被 git 忽略）

## 编码注意事项（踩过的坑）

这两个要求正好相反，改文件时别搞混：

- `tools/add-problem.ps1` 必须保存为 **UTF-8 带 BOM**。PowerShell 5.1 读无 BOM 的
  脚本会按 GBK 解码，中文乱码会破坏引号配对，整个脚本直接语法报错。
- `.java` 文件必须是 **UTF-8 无 BOM**。javac 遇到 BOM 会报
  `非法字符: '\ufeff'` 然后编译失败。

脚本内部一律用 `[IO.File]` 显式指定编码读写，没有用 `Set-Content -Encoding utf8`
—— 它在 PS 5.1 下会加 BOM、在 PS 7 下不加，行为不一致，会埋雷。

# leetcode-java

LeetCode 题解（Java）。

## 约定

- **一题一文件**，类名唯一：`P` + 四位题号 + 驼峰题名，例如 `P0001TwoSum`。
- **不写 `package` 声明**，文件按主题放在 `src/leetcode/<主题>/` 下。
  现有主题：`array`、`matrix`、`linked-list`、`tree`、`two-pointers`、`sliding-window`、`hash`、`dp`、`intervals`、`design`。
- 题目之外的**共用定义**放在 `src/leetcode/common/`（目前有 `ListNode`、`Node`、`TreeNode`）。LeetCode 把
  `ListNode` / `Node` / `TreeNode` 的定义写在每道题的注释里，复制出来不含可编译的定义，直接存盘会报
  「找不到符号」；而默认包里不能重复声明同名类，所以这类节点**统一用 `common/` 里的那一份**，各题文件里不要再各写一份。
  > `Node` 这个名字在 LeetCode 里被多种结构复用（随机链表 / 克隆图 / N 叉树 / 右侧指针），字段并不一样，
  > 目前 `common/Node.java` 是第 138 题那版（`val` / `next` / `random`）。
- 之所以每题都用唯一类名，是因为 Java 的默认包只有一个，多个叫 `Solution` 的类放在一起会编译报错「重复: Solution」。唯一类名让整个 `src` 可以一次性编译通过。

## 目录结构

```
leetcode-java/
├── README.md                     # 本文件，题目索引
├── .gitignore                    # 忽略 bin/ 等编译产物
├── .vscode/settings.json         # VS Code Java 工程配置
├── tools/
│   ├── add-problem.ps1           # 收录新题解的脚本
│   └── push.ps1                  # 带自动重试的 git push
└── src/
    └── leetcode/
        ├── common/
        │   ├── ListNode.java     # 链表题共用节点定义（不是某道题）
        │   ├── Node.java         # 带 random 指针的节点定义（第 138 题用）
        │   └── TreeNode.java     # 二叉树题共用节点定义
        ├── array/
        │   └── P0001TwoSum.java
        ├── design/
        │   └── P0146LRUCache.java
        ├── linked-list/
        │   ├── P0002AddTwoNumbers.java
        │   ├── P0019RemoveNthNodeFromEndOfList.java
        │   ├── P0021MergeTwoSortedLists.java
        │   ├── P0023MergeKSortedLists.java
        │   ├── P0024SwapNodesInPairs.java
        │   ├── P0025ReverseNodesInKGroup.java
        │   ├── P0138CopyListWithRandomPointer.java
        │   ├── P0141LinkedListCycle.java
        │   ├── P0142LinkedListCycleII.java
        │   ├── P0148SortList.java
        │   ├── P0160IntersectionOfTwoLinkedLists.java
        │   ├── P0206ReverseLinkedList.java
        │   └── P0234PalindromeLinkedList.java
        ├── matrix/
        │   ├── P0048RotateImage.java
        │   ├── P0054SpiralMatrix.java
        │   ├── P0073SetMatrixZeroes.java
        │   └── P0240SearchA2DMatrixII.java
        └── tree/
            ├── P0094BinaryTreeInorderTraversal.java
            ├── P0098ValidateBinarySearchTree.java
            ├── P0101SymmetricTree.java
            ├── P0102BinaryTreeLevelOrderTraversal.java
            ├── P0104MaximumDepthOfBinaryTree.java
            ├── P0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java
            ├── P0108ConvertSortedArrayToBinarySearchTree.java
            ├── P0114FlattenBinaryTreeToLinkedList.java
            ├── P0199BinaryTreeRightSideView.java
            ├── P0226InvertBinaryTree.java
            ├── P0230KthSmallestElementInABST.java
            ├── P0236LowestCommonAncestorOfABinaryTree.java
            ├── P0437PathSumIII.java
            └── P0543DiameterOfBinaryTree.java
```

## 推送（网络不稳时用这个）

国内访问 `github.com` 的连接经常**间歇性失败**，`git push` 会直接报
`Failed to connect to github.com port 443`，每次白等约 21 秒超时。用这个脚本代替：

```powershell
.\tools\push.ps1
```

它先做 3 秒超时的快速 TCP 探活，端口通了才真正推送，不通就等待重试，
默认最多 40 次、间隔 15 秒。参数可按需调：

```powershell
.\tools\push.ps1 -MaxAttempts 60 -IntervalSeconds 20
```

> 实测记录：同一批提交，有时 1 次成功，有时要等到第 6 次。这是随机波动，
> 重试就能解决；代码在本地提交后是安全的，不会丢。

## 题目索引

<!-- INDEX:START -->
| 题号 | 题目 | 难度 | 主题 | 解法要点 | 文件 |
| ---: | --- | :--: | --- | --- | --- |
| 1 | Two Sum / 两数之和 | Easy | array | 暴力双重循环，O(n²) | [P0001TwoSum.java](src/leetcode/array/P0001TwoSum.java) |
| 2 | Add Two Numbers / 两数相加 | Medium | linked-list | 逐位相加带 carry；短链补 0，循环结束后 carry>0 再补一个节点 | [P0002AddTwoNumbers.java](src/leetcode/linked-list/P0002AddTwoNumbers.java) |
| 3 | Longest Substring Without Repeating Characters / 无重复字符的最长子串 | Medium | sliding-window | 滑动窗口 + HashMap 记录字符上次出现位置，left 不回退 | [P0003LongestSubstringWithoutRepeatingCharacters.java](src/leetcode/sliding-window/P0003LongestSubstringWithoutRepeatingCharacters.java) |
| 11 | Container With Most Water / 盛最多水的容器 | Medium | two-pointers | 左右双指针，每次移动较矮的一侧，面积 O(n) 求最大 | [P0011ContainerWithMostWater.java](src/leetcode/two-pointers/P0011ContainerWithMostWater.java) |
| 15 | 3Sum / 三数之和 | Medium | two-pointers | 排序 + 双指针夹逼，三层去重 | [P0015ThreeSum.java](src/leetcode/two-pointers/P0015ThreeSum.java) |
| 19 | Remove Nth Node From End of List / 删除链表的倒数第 N 个结点 | Medium | linked-list | 两次遍历：先数长度 sz 再走到 sz-n 处跳过；注释附一次遍历的 dummy + 快慢指针最优解 | [P0019RemoveNthNodeFromEndOfList.java](src/leetcode/linked-list/P0019RemoveNthNodeFromEndOfList.java) |
| 21 | Merge Two Sorted Lists / 合并两个有序链表 | Easy | linked-list | 哑结点 + 双指针逐个接较小者；收尾接上未空的那条，返回 dummy.next | [P0021MergeTwoSortedLists.java](src/leetcode/linked-list/P0021MergeTwoSortedLists.java) |
| 23 | Merge k Sorted Lists / 合并K个升序链表 | Hard | linked-list | 分治合并（两两配对归并）：O(N log k) 时间，O(log k) 递归栈；注释附顺序归并 O(k^2 n) 对照版 | [P0023MergeKSortedLists.java](src/leetcode/linked-list/P0023MergeKSortedLists.java) |
| 24 | Swap Nodes in Pairs / 两两交换链表中的节点 | Medium | linked-list | 哑结点 + 三指针换序：先存下一组起点 nextPair 再改指针，避免丢失后续链 | [P0024SwapNodesInPairs.java](src/leetcode/linked-list/P0024SwapNodesInPairs.java) |
| 25 | Reverse Nodes in k-Group / K 个一组翻转链表 | Hard | linked-list | 哑结点 + 先数 k 个定位 end；断开本组整体反转后接回，不足 k 个保持原样 | [P0025ReverseNodesInKGroup.java](src/leetcode/linked-list/P0025ReverseNodesInKGroup.java) |
| 41 | First Missing Positive | Hard | array | 原地哈希：数字 x 放到下标 x-1，环形交换 | [P0041FirstMissingPositive.java](src/leetcode/array/P0041FirstMissingPositive.java) |
| 42 | Trapping Rain Water / 接雨水 | Hard | two-pointers | 双指针 + 左右最大高度，每次处理较矮的一侧 | [P0042TrappingRainWater.java](src/leetcode/two-pointers/P0042TrappingRainWater.java) |
| 48 | Rotate Image / 旋转图像 | Medium | matrix | 上下翻转 + 主对角线翻转 = 顺时针 90 度 | [P0048RotateImage.java](src/leetcode/matrix/P0048RotateImage.java) |
| 49 | Group Anagrams / 字母异位词分组 | Medium | hash | 排序后的字符串作哈希键，O(n·k log k) | [P0049GroupAnagrams.java](src/leetcode/hash/P0049GroupAnagrams.java) |
| 53 | Maximum Subarray / 最大子数组和 | Medium | dp | Kadane 动态规划：f(i)=max(f(i-1)+nums[i], nums[i])，滚动变量 O(1) 空间 | [P0053MaximumSubarray.java](src/leetcode/dp/P0053MaximumSubarray.java) |
| 54 | Spiral Matrix / 螺旋矩阵 | Medium | matrix | 四条边界收缩，逐圈取数 | [P0054SpiralMatrix.java](src/leetcode/matrix/P0054SpiralMatrix.java) |
| 56 | Merge Intervals / 合并区间 | Medium | intervals | 按左端点排序 + 一次遍历合并；返回前用 Arrays.copyOf 截取 size+1 个有效区间 | [P0056MergeIntervals.java](src/leetcode/intervals/P0056MergeIntervals.java) |
| 73 | Set Matrix Zeroes / 矩阵置零 | Medium | matrix | 首行首列当标记位，O(1) 额外空间 | [P0073SetMatrixZeroes.java](src/leetcode/matrix/P0073SetMatrixZeroes.java) |
| 76 | Minimum Window Substring / 最小覆盖子串 | Hard | sliding-window | 滑动窗口：r 扩张到覆盖 t，再收缩 l 取最小；need 计数判断是否覆盖，O(n) | [P0076MinimumWindowSubstring.java](src/leetcode/sliding-window/P0076MinimumWindowSubstring.java) |
| 94 | Binary Tree Inorder Traversal / 二叉树的中序遍历 | Easy | tree | 迭代法用显式栈模拟递归：一路压左孩子，弹出即访问，再转向右孩子；注释附递归版 | [P0094BinaryTreeInorderTraversal.java](src/leetcode/tree/P0094BinaryTreeInorderTraversal.java) |
| 98 | Validate Binary Search Tree / 验证二叉搜索树 | Medium | tree | 上下界传递：左子树收紧上界、右子树收紧下界，严格比较；上下界必须用 long 装 ±∞ 才能扛住 Integer.MIN/MAX_VALUE | [P0098ValidateBinarySearchTree.java](src/leetcode/tree/P0098ValidateBinarySearchTree.java) |
| 101 | Symmetric Tree / 对称二叉树 | Easy | tree | 递归比较镜像对：左的左 vs 右的右、左的右 vs 右的左（交叉比较才是镜像） | [P0101SymmetricTree.java](src/leetcode/tree/P0101SymmetricTree.java) |
| 102 | Binary Tree Level Order Traversal / 二叉树的层序遍历 | Medium | tree | BFS + 队列：每层开始前用 queue.size() 记住本层结点数，一口气处理完这一层（注释附 DFS 递归版） | [P0102BinaryTreeLevelOrderTraversal.java](src/leetcode/tree/P0102BinaryTreeLevelOrderTraversal.java) |
| 104 | Maximum Depth of Binary Tree / 二叉树的最大深度 | Easy | tree | 后序递归：空结点返回 0，否则 max(左深度, 右深度) + 1 | [P0104MaximumDepthOfBinaryTree.java](src/leetcode/tree/P0104MaximumDepthOfBinaryTree.java) |
| 105 | Construct Binary Tree from Preorder and Inorder Traversal / 从前序与中序遍历序列构造二叉树 | Medium | tree | 分治 + 半开区间 [start,end)：先序首元素是根，在中序里定位根把两组序列切成左右两段；把中序下标预存进哈希表可把 O(n^2) 降到 O(n) | [P0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java](src/leetcode/tree/P0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java) |
| 108 | Convert Sorted Array to Binary Search Tree / 将有序数组转换为二叉搜索树 | Easy | tree | 取区间中点作根（mid=(left+right)/2 取中间偏左），递归建左右子树 → 天然高度平衡；空区间返回 null | [P0108ConvertSortedArrayToBinarySearchTree.java](src/leetcode/tree/P0108ConvertSortedArrayToBinarySearchTree.java) |
| 114 | Flatten Binary Tree to Linked List / 二叉树展开为链表 | Medium | tree | Morris 风格原地展开 O(1) 空间：有左子树时先找左子树最右结点、把原右子树接上去，再把左子树搬到右边并置空 left；展平顺序 = 先序 | [P0114FlattenBinaryTreeToLinkedList.java](src/leetcode/tree/P0114FlattenBinaryTreeToLinkedList.java) |
| 128 | Longest Consecutive Sequence / 最长连续序列 | Medium | hash | HashSet O(n) 查找，只从序列起点(num-1 不存在)开始扩展 | [P0128LongestConsecutiveSequence.java](src/leetcode/hash/P0128LongestConsecutiveSequence.java) |
| 138 | Copy List with Random Pointer / 随机链表的复制 | Medium | linked-list | 拼接 + 拆分：原/新节点交错成一条链，cur.random.next 即新节点的 random；最后拆回两条链并单独处理原链尾 | [P0138CopyListWithRandomPointer.java](src/leetcode/linked-list/P0138CopyListWithRandomPointer.java) |
| 141 | Linked List Cycle / 环形链表 | Easy | linked-list | 快慢指针 Floyd 判圈：fast 从 head.next 出发避免初始即相遇；相遇→有环，fast 越界→无环 | [P0141LinkedListCycle.java](src/leetcode/linked-list/P0141LinkedListCycle.java) |
| 142 | Linked List Cycle II / 环形链表 II | Medium | linked-list | Floyd 判圈后，ptr 从 head 与 slow 同速前进，相遇处即入环点（因 a = kL - b） | [P0142LinkedListCycleII.java](src/leetcode/linked-list/P0142LinkedListCycleII.java) |
| 146 | LRU Cache / LRU 缓存 | Medium | design | 哈希表 + 双向链表（伪头/伪尾哨兵）：get/put 均 O(1)；put 超容量时摘掉尾结点并从表中删掉它的 key | [P0146LRUCache.java](src/leetcode/design/P0146LRUCache.java) |
| 148 | Sort List / 排序链表 | Medium | linked-list | 自底向上归并排序（迭代）：每轮按 subLength 切段两两合并，O(n log n) 时间 O(1) 空间 | [P0148SortList.java](src/leetcode/linked-list/P0148SortList.java) |
| 160 | Intersection of Two Linked Lists / 相交链表 | Easy | linked-list | 双指针互换链表头，各自走 a+b-c 步后相遇；不相交则同时为 null | [P0160IntersectionOfTwoLinkedLists.java](src/leetcode/linked-list/P0160IntersectionOfTwoLinkedLists.java) |
| 189 | Rotate Array | Medium | array | 三次反转，原地 O(1) 空间 | [P0189RotateArray.java](src/leetcode/array/P0189RotateArray.java) |
| 199 | Binary Tree Right Side View / 二叉树的右视图 | Medium | tree | DFS 右优先：先递归右子树，depth == ans.size() 表示该深度首次到达，正好是最右可见结点；注释附层序版 | [P0199BinaryTreeRightSideView.java](src/leetcode/tree/P0199BinaryTreeRightSideView.java) |
| 206 | Reverse Linked List / 反转链表 | Easy | linked-list | 迭代：pre/cur 双指针，逐个把 next 指向前驱，O(n) 时间 O(1) 空间 | [P0206ReverseLinkedList.java](src/leetcode/linked-list/P0206ReverseLinkedList.java) |
| 226 | Invert Binary Tree / 翻转二叉树 | Easy | tree | 后序递归：先翻好左右子树，再交换 root 的左右孩子；空结点返回 null | [P0226InvertBinaryTree.java](src/leetcode/tree/P0226InvertBinaryTree.java) |
| 230 | Kth Smallest Element in a BST / 二叉搜索树中第K小的元素 | Medium | tree | 迭代中序（94 的骨架）+ 计数器：每弹出一个结点 k--，减到 0 即答案，找到就 break 不用遍历全树，O(H+k) | [P0230KthSmallestElementInABST.java](src/leetcode/tree/P0230KthSmallestElementInABST.java) |
| 234 | Palindrome Linked List / 回文链表 | Easy | linked-list | 快慢指针找中点 + 反转后半段比对；比对完再反转回来恢复原链表，O(1) 空间 | [P0234PalindromeLinkedList.java](src/leetcode/linked-list/P0234PalindromeLinkedList.java) |
| 236 | Lowest Common Ancestor of a Binary Tree / 二叉树的最近公共祖先 | Medium | tree | 后序递归：root 为空或等于 p/q 就直接返回 root；左右结果只有一个非空就返回那个，两个都非空说明 p、q 分居两侧，root 即 LCA | [P0236LowestCommonAncestorOfABinaryTree.java](src/leetcode/tree/P0236LowestCommonAncestorOfABinaryTree.java) |
| 238 | Product of Array Except Self | Medium | array | 前后缀积（left/right 两个数组） | [P0238ProductOfArrayExceptSelf.java](src/leetcode/array/P0238ProductOfArrayExceptSelf.java) |
| 239 | Sliding Window Maximum / 滑动窗口最大值 | Hard | sliding-window | 优先队列存 [数值,下标]，堆顶最大；取堆顶时弹出已滑出窗口的过期元素，O(n log n) | [P0239SlidingWindowMaximum.java](src/leetcode/sliding-window/P0239SlidingWindowMaximum.java) |
| 240 | Search a 2D Matrix II / 搜索二维矩阵 II | Medium | matrix | 左下角标志数：> target 消一行，< target 消一列 | [P0240SearchA2DMatrixII.java](src/leetcode/matrix/P0240SearchA2DMatrixII.java) |
| 283 | Move Zeroes / 移动零 | Easy | two-pointers | 快慢双指针，非零元素依次换到前面，零自动沉底 | [P0283MoveZeroes.java](src/leetcode/two-pointers/P0283MoveZeroes.java) |
| 437 | Path Sum III / 路径总和 III | Medium | tree | 前缀和 + 回溯：根到当前结点的前缀和存进 HashMap，curSum-target 的出现次数就是以当前结点结尾的合法路径数；离开子树时撤销状态，保证只统计祖先 | [P0437PathSumIII.java](src/leetcode/tree/P0437PathSumIII.java) |
| 438 | Find All Anagrams in a String / 找到字符串中所有字母异位词 | Medium | sliding-window | 固定长度滑动窗口 + 26 位计数数组，Arrays.equals 比较 | [P0438FindAllAnagramsInAString.java](src/leetcode/sliding-window/P0438FindAllAnagramsInAString.java) |
| 543 | Diameter of Binary Tree / 二叉树的直径 | Easy | tree | 在 104 的骨架上加一句：每到一个结点就用「左深+右深」更新全局最大直径；空返回 0，叶子返回 1 | [P0543DiameterOfBinaryTree.java](src/leetcode/tree/P0543DiameterOfBinaryTree.java) |
| 560 | Subarray Sum Equals K / 和为 K 的子数组 | Medium | array | 暴力枚举所有子数组，O(n²) | [P0560SubarraySumEqualsK.java](src/leetcode/array/P0560SubarraySumEqualsK.java) |
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

# leetcode-java

LeetCode 题解（Java）。

## 约定

- **一题一文件**，类名唯一：`P` + 四位题号 + 驼峰题名，例如 `P0001TwoSum`。
- **不写 `package` 声明**，文件按主题放在 `src/leetcode/<主题>/` 下。
  现有主题：`array`、`matrix`、`linked-list`、`tree`、`graph`、`heap`、`backtracking`、`binary-search`、`stack`、`two-pointers`、`sliding-window`、`hash`、`dp`、`intervals`、`design`。
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
        ├── backtracking/
        │   ├── P0017LetterCombinationsOfAPhoneNumber.java
        │   ├── P0022GenerateParentheses.java
        │   ├── P0039CombinationSum.java
        │   ├── P0046Permutations.java
        │   ├── P0051NQueens.java
        │   ├── P0078Subsets.java
        │   ├── P0079WordSearch.java
        │   └── P0131PalindromePartitioning.java
        ├── binary-search/
        │   ├── P0004MedianOfTwoSortedArrays.java
        │   ├── P0033SearchInRotatedSortedArray.java
        │   ├── P0034FindFirstAndLastPositionOfElementInSortedArray.java
        │   ├── P0035SearchInsertPosition.java
        │   ├── P0074SearchA2DMatrix.java
        │   └── P0153FindMinimumInRotatedSortedArray.java
        ├── design/
        │   ├── P0146LRUCache.java
        │   └── P0208ImplementTriePrefixTree.java
        ├── dp/
        │   ├── P0045JumpGameII.java
        │   ├── P0053MaximumSubarray.java
        │   ├── P0055JumpGame.java
        │   ├── P0062UniquePaths.java
        │   ├── P0064MinimumPathSum.java
        │   ├── P0070ClimbingStairs.java
        │   ├── P0118PascalsTriangle.java
        │   ├── P0121BestTimeToBuyAndSellStock.java
        │   ├── P0139WordBreak.java
        │   ├── P0152MaximumProductSubarray.java
        │   ├── P0198HouseRobber.java
        │   ├── P0279PerfectSquares.java
        │   ├── P0300LongestIncreasingSubsequence.java
        │   ├── P0322CoinChange.java
        │   └── P0416PartitionEqualSubsetSum.java
        ├── graph/
        │   ├── P0200NumberOfIslands.java
        │   ├── P0207CourseSchedule.java
        │   └── P0994RottingOranges.java
        ├── heap/
        │   ├── P0215KthLargestElementInAnArray.java
        │   ├── P0295FindMedianFromDataStream.java
        │   └── P0347TopKFrequentElements.java
        ├── intervals/
        │   ├── P0056MergeIntervals.java
        │   └── P0763PartitionLabels.java
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
        ├── stack/
        │   ├── P0020ValidParentheses.java
        │   ├── P0032LongestValidParentheses.java
        │   ├── P0084LargestRectangleInHistogram.java
        │   ├── P0155MinStack.java
        │   ├── P0394DecodeString.java
        │   └── P0739DailyTemperatures.java
        └── tree/
            ├── P0094BinaryTreeInorderTraversal.java
            ├── P0098ValidateBinarySearchTree.java
            ├── P0101SymmetricTree.java
            ├── P0102BinaryTreeLevelOrderTraversal.java
            ├── P0104MaximumDepthOfBinaryTree.java
            ├── P0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java
            ├── P0108ConvertSortedArrayToBinarySearchTree.java
            ├── P0114FlattenBinaryTreeToLinkedList.java
            ├── P0124BinaryTreeMaximumPathSum.java
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
| 4 | Median of Two Sorted Arrays / 寻找两个正序数组的中位数 | Hard | binary-search | 转成「求第 k 小」：中位数 = 第 (m+n+1)/2 小和第 (m+n+2)/2 小的平均（奇偶统一）；findKth 每次比较两个数组各自的第 k/2 个元素，淘汰较小那侧的前 k/2 个、k 减去 k/2，直到 k==1 或某个数组耗尽 | [P0004MedianOfTwoSortedArrays.java](src/leetcode/binary-search/P0004MedianOfTwoSortedArrays.java) |
| 11 | Container With Most Water / 盛最多水的容器 | Medium | two-pointers | 左右双指针，每次移动较矮的一侧，面积 O(n) 求最大 | [P0011ContainerWithMostWater.java](src/leetcode/two-pointers/P0011ContainerWithMostWater.java) |
| 15 | 3Sum / 三数之和 | Medium | two-pointers | 排序 + 双指针夹逼，三层去重 | [P0015ThreeSum.java](src/leetcode/two-pointers/P0015ThreeSum.java) |
| 17 | Letter Combinations of a Phone Number / 电话号码的字母组合 | Medium | backtracking | 回溯：每位数字遍历它的字母，append 后递归、回来后 deleteCharAt；index == digits.length() 说明拼满一个结果（空输入要单独返回 []） | [P0017LetterCombinationsOfAPhoneNumber.java](src/leetcode/backtracking/P0017LetterCombinationsOfAPhoneNumber.java) |
| 19 | Remove Nth Node From End of List / 删除链表的倒数第 N 个结点 | Medium | linked-list | 两次遍历：先数长度 sz 再走到 sz-n 处跳过；注释附一次遍历的 dummy + 快慢指针最优解 | [P0019RemoveNthNodeFromEndOfList.java](src/leetcode/linked-list/P0019RemoveNthNodeFromEndOfList.java) |
| 20 | Valid Parentheses / 有效的括号 | Easy | stack | 栈 + 反向压入：遇到左括号就压入「它对应的右括号」，遇到右括号就弹出比较；先判空再 pop（靠 || 短路），最后栈必须为空 | [P0020ValidParentheses.java](src/leetcode/stack/P0020ValidParentheses.java) |
| 21 | Merge Two Sorted Lists / 合并两个有序链表 | Easy | linked-list | 哑结点 + 双指针逐个接较小者；收尾接上未空的那条，返回 dummy.next | [P0021MergeTwoSortedLists.java](src/leetcode/linked-list/P0021MergeTwoSortedLists.java) |
| 22 | Generate Parentheses / 括号生成 | Medium | backtracking | 回溯 + 两个剪枝条件：open<n 才能加左括号，close<open 才能加右括号（保证任意前缀合法）；len==2n 收结果，结果总数是第 n 个 Catalan 数 | [P0022GenerateParentheses.java](src/leetcode/backtracking/P0022GenerateParentheses.java) |
| 23 | Merge k Sorted Lists / 合并K个升序链表 | Hard | linked-list | 分治合并（两两配对归并）：O(N log k) 时间，O(log k) 递归栈；注释附顺序归并 O(k^2 n) 对照版 | [P0023MergeKSortedLists.java](src/leetcode/linked-list/P0023MergeKSortedLists.java) |
| 24 | Swap Nodes in Pairs / 两两交换链表中的节点 | Medium | linked-list | 哑结点 + 三指针换序：先存下一组起点 nextPair 再改指针，避免丢失后续链 | [P0024SwapNodesInPairs.java](src/leetcode/linked-list/P0024SwapNodesInPairs.java) |
| 25 | Reverse Nodes in k-Group / K 个一组翻转链表 | Hard | linked-list | 哑结点 + 先数 k 个定位 end；断开本组整体反转后接回，不足 k 个保持原样 | [P0025ReverseNodesInKGroup.java](src/leetcode/linked-list/P0025ReverseNodesInKGroup.java) |
| 32 | Longest Valid Parentheses / 最长有效括号 | Hard | stack | 栈标记法：栈里只存未匹配的左括号下标，多余的右括号当场标记无效，遍历完再把栈里剩下的左括号一并标记，问题转成「在标记数组里找最长连续 1」；O(n) 时间 O(n) 空间。文件末尾附注释版 O(n) 动态规划解法（dp[i] 为以 s[i] 结尾的最长有效长度） | [P0032LongestValidParentheses.java](src/leetcode/stack/P0032LongestValidParentheses.java) |
| 33 | Search in Rotated Sorted Array / 搜索旋转排序数组 | Medium | binary-search | 旋转数组二分：每次 mid 把区间切成两半，必有一半完全有序；先判断哪一半有序，再用「有序那一半的边界」判断 target 是否落在里面，落在里面就收缩到那一半，否则去另一半 | [P0033SearchInRotatedSortedArray.java](src/leetcode/binary-search/P0033SearchInRotatedSortedArray.java) |
| 34 | Find First and Last Position of Element in Sorted Array / 在排序数组中查找元素的第一个和最后一个位置 | Medium | binary-search | 两次二分：命中 target 时先记录 mid，再往左继续搜 first、往右继续搜 last；没命中过就保持 -1 | [P0034FindFirstAndLastPositionOfElementInSortedArray.java](src/leetcode/binary-search/P0034FindFirstAndLastPositionOfElementInSortedArray.java) |
| 35 | Search Insert Position / 搜索插入位置 | Easy | binary-search | 二分找「第一个 >= target 的位置」：nums[mid] < target 就 l=mid+1，否则 r=mid-1，循环结束时 l 就是插入位置；mid 用 l+(r-l)/2 防溢出 | [P0035SearchInsertPosition.java](src/leetcode/binary-search/P0035SearchInsertPosition.java) |
| 39 | Combination Sum / 组合总和 | Medium | backtracking | 组合式回溯 + 元素可重复：递归传 i（不是 i+1）就能重复选；sum 作为参数往下传（写在调用里），避开手动加减；sum==target 收结果、sum>target 剪枝返回 | [P0039CombinationSum.java](src/leetcode/backtracking/P0039CombinationSum.java) |
| 41 | First Missing Positive | Hard | array | 原地哈希：数字 x 放到下标 x-1，环形交换 | [P0041FirstMissingPositive.java](src/leetcode/array/P0041FirstMissingPositive.java) |
| 42 | Trapping Rain Water / 接雨水 | Hard | two-pointers | 双指针 + 左右最大高度，每次处理较矮的一侧 | [P0042TrappingRainWater.java](src/leetcode/two-pointers/P0042TrappingRainWater.java) |
| 45 | Jump Game II / 跳跃游戏 II | Medium | dp | BFS 式贪心：border 是当前这一跳能覆盖的最远边界，maxPosition 是「边界内各格再跳一次」能到的最远位置；走到 border 就必须跳一次（step++）并把 border 推到 maxPosition；O(n) 时间 O(1) 空间 | [P0045JumpGameII.java](src/leetcode/dp/P0045JumpGameII.java) |
| 46 | Permutations / 全排列 | Medium | backtracking | 回溯模板：used[] 标记已选 + path 记录当前排列；到头就【深拷贝】path 存进结果，然后撤销选择回退（一定要 new ArrayList<>(path) 复制） | [P0046Permutations.java](src/leetcode/backtracking/P0046Permutations.java) |
| 48 | Rotate Image / 旋转图像 | Medium | matrix | 上下翻转 + 主对角线翻转 = 顺时针 90 度 | [P0048RotateImage.java](src/leetcode/matrix/P0048RotateImage.java) |
| 49 | Group Anagrams / 字母异位词分组 | Medium | hash | 排序后的字符串作哈希键，O(n·k log k) | [P0049GroupAnagrams.java](src/leetcode/hash/P0049GroupAnagrams.java) |
| 51 | N-Queens / N 皇后 | Hard | backtracking | 回溯 + 三个冲突集合：columns 记列、row-col 记「左上→右下」对角线、row+col 记「右上→左下」对角线；每行放一个皇后，放满 n 行就输出棋盘 | [P0051NQueens.java](src/leetcode/backtracking/P0051NQueens.java) |
| 53 | Maximum Subarray / 最大子数组和 | Medium | dp | Kadane 动态规划：f(i)=max(f(i-1)+nums[i], nums[i])，滚动变量 O(1) 空间 | [P0053MaximumSubarray.java](src/leetcode/dp/P0053MaximumSubarray.java) |
| 54 | Spiral Matrix / 螺旋矩阵 | Medium | matrix | 四条边界收缩，逐圈取数 | [P0054SpiralMatrix.java](src/leetcode/matrix/P0054SpiralMatrix.java) |
| 55 | Jump Game / 跳跃游戏 | Medium | dp | 贪心：把可跳步数当成「剩余能量」，每走一格消耗 1；能量为负说明到不了当前格，遇到更大的步数就替换（等价于维护「最远可达位置 - 当前位置」）；注释附倒序「最早能到终点的位置」写法 | [P0055JumpGame.java](src/leetcode/dp/P0055JumpGame.java) |
| 56 | Merge Intervals / 合并区间 | Medium | intervals | 按左端点排序 + 一次遍历合并；返回前用 Arrays.copyOf 截取 size+1 个有效区间 | [P0056MergeIntervals.java](src/leetcode/intervals/P0056MergeIntervals.java) |
| 62 | Unique Paths / 不同路径 | Medium | dp | 二维 DP：dp[i][j] = dp[i-1][j] + dp[i][j-1]，第一行/第一列全为 1 作边界（只能一直向右/向下走），答案取 dp[m-1][n-1]；O(m×n) 时间 O(m×n) 空间。文件末尾附注释版 O(n) 空间滚动数组优化（cur[j] += cur[j-1]） | [P0062UniquePaths.java](src/leetcode/dp/P0062UniquePaths.java) |
| 64 | Minimum Path Sum / 最小路径和 | Medium | dp | 网格 DP：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]，第一行/第一列只能沿一个方向累加（前缀和）；O(m×n) 时间 O(m×n) 空间，可滚成一维 O(n) | [P0064MinimumPathSum.java](src/leetcode/dp/P0064MinimumPathSum.java) |
| 70 | Climbing Stairs / 爬楼梯 | Easy | dp | 线性 DP（斐波那契）：f(n) = f(n-1) + f(n-2)；用三个滚动变量把空间压到 O(1)，迭代 n 次，每次 num1<-num2、num2<-res、res<-num1+num2 | [P0070ClimbingStairs.java](src/leetcode/dp/P0070ClimbingStairs.java) |
| 73 | Set Matrix Zeroes / 矩阵置零 | Medium | matrix | 首行首列当标记位，O(1) 额外空间 | [P0073SetMatrixZeroes.java](src/leetcode/matrix/P0073SetMatrixZeroes.java) |
| 74 | Search a 2D Matrix / 搜索二维矩阵 | Medium | binary-search | 两次二分：先在第一列上二分定位「第一个 matrix[u][0] >= target」的行 u，命中行首直接 true；否则在 u-1 行内再二分。注意 u==0（比所有都小）和 u==m（比所有都大）两个边界 | [P0074SearchA2DMatrix.java](src/leetcode/binary-search/P0074SearchA2DMatrix.java) |
| 76 | Minimum Window Substring / 最小覆盖子串 | Hard | sliding-window | 滑动窗口：r 扩张到覆盖 t，再收缩 l 取最小；need 计数判断是否覆盖，O(n) | [P0076MinimumWindowSubstring.java](src/leetcode/sliding-window/P0076MinimumWindowSubstring.java) |
| 78 | Subsets / 子集 | Medium | backtracking | 组合式回溯：每进一层就先把当前前缀收进结果，再从 cur 往后挑元素、递归 i+1；靠起点 cur 去重所以不需要 used 数组 | [P0078Subsets.java](src/leetcode/backtracking/P0078Subsets.java) |
| 79 | Word Search / 单词搜索 | Medium | backtracking | 网格 DFS 回溯：字符不匹配/到头分别返回 false/true；进递归前标记 isVisited、出来必须撤销；外层对每个起点试一次 | [P0079WordSearch.java](src/leetcode/backtracking/P0079WordSearch.java) |
| 84 | Largest Rectangle in Histogram / 柱状图中最大的矩形 | Hard | stack | 单调栈求左右边界：栈里存下标，弹出时确定右边界（右边第一个高度 <= 自己的位置），进栈前栈顶就是左边界（左边第一个比自己矮的位置）；面积 = (right-left-1)*height；相等高度也要弹（所以用 <=），右边界默认 n | [P0084LargestRectangleInHistogram.java](src/leetcode/stack/P0084LargestRectangleInHistogram.java) |
| 94 | Binary Tree Inorder Traversal / 二叉树的中序遍历 | Easy | tree | 迭代法用显式栈模拟递归：一路压左孩子，弹出即访问，再转向右孩子；注释附递归版 | [P0094BinaryTreeInorderTraversal.java](src/leetcode/tree/P0094BinaryTreeInorderTraversal.java) |
| 98 | Validate Binary Search Tree / 验证二叉搜索树 | Medium | tree | 上下界传递：左子树收紧上界、右子树收紧下界，严格比较；上下界必须用 long 装 ±∞ 才能扛住 Integer.MIN/MAX_VALUE | [P0098ValidateBinarySearchTree.java](src/leetcode/tree/P0098ValidateBinarySearchTree.java) |
| 101 | Symmetric Tree / 对称二叉树 | Easy | tree | 递归比较镜像对：左的左 vs 右的右、左的右 vs 右的左（交叉比较才是镜像） | [P0101SymmetricTree.java](src/leetcode/tree/P0101SymmetricTree.java) |
| 102 | Binary Tree Level Order Traversal / 二叉树的层序遍历 | Medium | tree | BFS + 队列：每层开始前用 queue.size() 记住本层结点数，一口气处理完这一层（注释附 DFS 递归版） | [P0102BinaryTreeLevelOrderTraversal.java](src/leetcode/tree/P0102BinaryTreeLevelOrderTraversal.java) |
| 104 | Maximum Depth of Binary Tree / 二叉树的最大深度 | Easy | tree | 后序递归：空结点返回 0，否则 max(左深度, 右深度) + 1 | [P0104MaximumDepthOfBinaryTree.java](src/leetcode/tree/P0104MaximumDepthOfBinaryTree.java) |
| 105 | Construct Binary Tree from Preorder and Inorder Traversal / 从前序与中序遍历序列构造二叉树 | Medium | tree | 分治 + 半开区间 [start,end)：先序首元素是根，在中序里定位根把两组序列切成左右两段；把中序下标预存进哈希表可把 O(n^2) 降到 O(n) | [P0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java](src/leetcode/tree/P0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java) |
| 108 | Convert Sorted Array to Binary Search Tree / 将有序数组转换为二叉搜索树 | Easy | tree | 取区间中点作根（mid=(left+right)/2 取中间偏左），递归建左右子树 → 天然高度平衡；空区间返回 null | [P0108ConvertSortedArrayToBinarySearchTree.java](src/leetcode/tree/P0108ConvertSortedArrayToBinarySearchTree.java) |
| 114 | Flatten Binary Tree to Linked List / 二叉树展开为链表 | Medium | tree | Morris 风格原地展开 O(1) 空间：有左子树时先找左子树最右结点、把原右子树接上去，再把左子树搬到右边并置空 left；展平顺序 = 先序 | [P0114FlattenBinaryTreeToLinkedList.java](src/leetcode/tree/P0114FlattenBinaryTreeToLinkedList.java) |
| 118 | Pascal's Triangle / 杨辉三角 | Easy | dp | 杨辉三角：第 i 行有 i+1 个数，两头都是 1，中间 = 上一行相邻两项之和 res[i-1][j-1] + res[i-1][j]；O(n^2) 时间与空间（输出本身就这么大） | [P0118PascalsTriangle.java](src/leetcode/dp/P0118PascalsTriangle.java) |
| 121 | Best Time to Buy and Sell Stock / 买卖股票的最佳时机 | Easy | dp | 一次遍历（DP/贪心）：维护「到今天为止的最低买入价」，每天用「今天价格 - 最低价」更新最大收益；等价于 f(i) = max(f(i-1), 今天价 - 前 i-1 天最低价)；O(n) 时间 O(1) 空间 | [P0121BestTimeToBuyAndSellStock.java](src/leetcode/dp/P0121BestTimeToBuyAndSellStock.java) |
| 124 | Binary Tree Maximum Path Sum / 二叉树中的最大路径和 | Hard | tree | 树形 DP：返回给上层的是「单边最大贡献」max(左,右)+val，而用来更新答案的是「可以分叉的拱形路径」左+右+val；子树贡献为负就取 0（不选这条分支） | [P0124BinaryTreeMaximumPathSum.java](src/leetcode/tree/P0124BinaryTreeMaximumPathSum.java) |
| 128 | Longest Consecutive Sequence / 最长连续序列 | Medium | hash | HashSet O(n) 查找，只从序列起点(num-1 不存在)开始扩展 | [P0128LongestConsecutiveSequence.java](src/leetcode/hash/P0128LongestConsecutiveSequence.java) |
| 131 | Palindrome Partitioning / 分割回文串 | Medium | backtracking | 回溯 + DP 预处理：flag[i][j] 记 s[i..j] 是否回文，按 i 从大到小填（依赖 i+1 行）；dfs 枚举每段终点 j，只有 flag[i][j] 为真才切，切到最后收结果 | [P0131PalindromePartitioning.java](src/leetcode/backtracking/P0131PalindromePartitioning.java) |
| 138 | Copy List with Random Pointer / 随机链表的复制 | Medium | linked-list | 拼接 + 拆分：原/新节点交错成一条链，cur.random.next 即新节点的 random；最后拆回两条链并单独处理原链尾 | [P0138CopyListWithRandomPointer.java](src/leetcode/linked-list/P0138CopyListWithRandomPointer.java) |
| 139 | Word Break / 单词拆分 | Medium | dp | 完全背包式可行性 DP：dp[i] 表示前缀 s[0,i) 能否被拆成字典单词，枚举分割点 j，只要 dp[j] 为真且 s[j,i) 在字典里就置 dp[i]=true 并 break；O(n²) 次判断（含 substring 拷贝约 O(n³) 字符操作），O(n) 空间 | [P0139WordBreak.java](src/leetcode/dp/P0139WordBreak.java) |
| 141 | Linked List Cycle / 环形链表 | Easy | linked-list | 快慢指针 Floyd 判圈：fast 从 head.next 出发避免初始即相遇；相遇→有环，fast 越界→无环 | [P0141LinkedListCycle.java](src/leetcode/linked-list/P0141LinkedListCycle.java) |
| 142 | Linked List Cycle II / 环形链表 II | Medium | linked-list | Floyd 判圈后，ptr 从 head 与 slow 同速前进，相遇处即入环点（因 a = kL - b） | [P0142LinkedListCycleII.java](src/leetcode/linked-list/P0142LinkedListCycleII.java) |
| 146 | LRU Cache / LRU 缓存 | Medium | design | 哈希表 + 双向链表（伪头/伪尾哨兵）：get/put 均 O(1)；put 超容量时摘掉尾结点并从表中删掉它的 key | [P0146LRUCache.java](src/leetcode/design/P0146LRUCache.java) |
| 148 | Sort List / 排序链表 | Medium | linked-list | 自底向上归并排序（迭代）：每轮按 subLength 切段两两合并，O(n log n) 时间 O(1) 空间 | [P0148SortList.java](src/leetcode/linked-list/P0148SortList.java) |
| 152 | Maximum Product Subarray / 乘积最大子数组 | Medium | dp | 双状态 DP：maxDP[i] / minDP[i] 分别表示以 nums[i] 结尾的连续子数组的最大、最小乘积，转移取 {nums[i], maxDP[i-1]*nums[i], minDP[i-1]*nums[i]} 三者极值，负负得正靠 minDP 记住；答案取全表 maxDP 最大值；O(n) 时间 O(n) 空间 | [P0152MaximumProductSubarray.java](src/leetcode/dp/P0152MaximumProductSubarray.java) |
| 153 | Find Minimum in Rotated Sorted Array / 寻找旋转排序数组中的最小值 | Medium | binary-search | 二分：只比较 nums[mid] 和 nums[r] —— 比 r 小说明最小值在 [l,mid]（mid 本身也可能是答案，所以 r=mid 而不是 mid-1）；比 r 大说明在 (mid,r] 取 l=mid+1；l==r 时就是最小值 | [P0153FindMinimumInRotatedSortedArray.java](src/leetcode/binary-search/P0153FindMinimumInRotatedSortedArray.java) |
| 155 | Min Stack / 最小栈 | Medium | stack | 差值法：栈里只存「val - 当前min」的差值（long 防溢出），并维护一个 min 变量；diff<0 说明新值是新的最小值，pop 时若 diff<0 就用 min-diff 回滚；O(1) 额外空间 | [P0155MinStack.java](src/leetcode/stack/P0155MinStack.java) |
| 160 | Intersection of Two Linked Lists / 相交链表 | Easy | linked-list | 双指针互换链表头，各自走 a+b-c 步后相遇；不相交则同时为 null | [P0160IntersectionOfTwoLinkedLists.java](src/leetcode/linked-list/P0160IntersectionOfTwoLinkedLists.java) |
| 189 | Rotate Array | Medium | array | 三次反转，原地 O(1) 空间 | [P0189RotateArray.java](src/leetcode/array/P0189RotateArray.java) |
| 198 | House Robber / 打家劫舍 | Medium | dp | 线性 DP：maxN[i] = max(前 i-1 间的最优, 前 i-2 间的最优 + 第 i 间)；两种状态「偷 / 不偷」取最大；可用两个滚动变量把 O(n) 空间压到 O(1) | [P0198HouseRobber.java](src/leetcode/dp/P0198HouseRobber.java) |
| 199 | Binary Tree Right Side View / 二叉树的右视图 | Medium | tree | DFS 右优先：先递归右子树，depth == ans.size() 表示该深度首次到达，正好是最右可见结点；注释附层序版 | [P0199BinaryTreeRightSideView.java](src/leetcode/tree/P0199BinaryTreeRightSideView.java) |
| 200 | Number of Islands / 岛屿数量 | Medium | graph | 网格 DFS 淹没法：外层每碰到一块 '1' 就计数 +1，并 dfs 把整座岛标成 '2'；访问过的格子改值即完成去重，整体 O(mn) | [P0200NumberOfIslands.java](src/leetcode/graph/P0200NumberOfIslands.java) |
| 206 | Reverse Linked List / 反转链表 | Easy | linked-list | 迭代：pre/cur 双指针，逐个把 next 指向前驱，O(n) 时间 O(1) 空间 | [P0206ReverseLinkedList.java](src/leetcode/linked-list/P0206ReverseLinkedList.java) |
| 207 | Course Schedule / 课程表 | Medium | graph | Kahn 拓扑排序（BFS）：入度数组 + 邻接表，每次取入度为 0 的课入队，出队时把后继入度减 1；最后看完成的课程数是否等于总数（不等即有环） | [P0207CourseSchedule.java](src/leetcode/graph/P0207CourseSchedule.java) |
| 208 | Implement Trie (Prefix Tree) / 实现 Trie (前缀树) | Medium | design | 26 叉树：children[26] 存子节点、isEnd 标记单词结尾；search = 走到底且 isEnd，startsWith = 只要走得到；insert/search/startsWith 都是 O(L) | [P0208ImplementTriePrefixTree.java](src/leetcode/design/P0208ImplementTriePrefixTree.java) |
| 215 | Kth Largest Element in an Array / 数组中的第K个最大元素 | Medium | heap | 手写大根堆：先 buildMaxHeap（从最后一个非叶子结点倒序 heapify），再弹出 k-1 次堆顶（换到末尾、缩小堆、重新 heapify），最后 nums[0] 就是第 k 大；O(n + k log n) | [P0215KthLargestElementInAnArray.java](src/leetcode/heap/P0215KthLargestElementInAnArray.java) |
| 226 | Invert Binary Tree / 翻转二叉树 | Easy | tree | 后序递归：先翻好左右子树，再交换 root 的左右孩子；空结点返回 null | [P0226InvertBinaryTree.java](src/leetcode/tree/P0226InvertBinaryTree.java) |
| 230 | Kth Smallest Element in a BST / 二叉搜索树中第K小的元素 | Medium | tree | 迭代中序（94 的骨架）+ 计数器：每弹出一个结点 k--，减到 0 即答案，找到就 break 不用遍历全树，O(H+k) | [P0230KthSmallestElementInABST.java](src/leetcode/tree/P0230KthSmallestElementInABST.java) |
| 234 | Palindrome Linked List / 回文链表 | Easy | linked-list | 快慢指针找中点 + 反转后半段比对；比对完再反转回来恢复原链表，O(1) 空间 | [P0234PalindromeLinkedList.java](src/leetcode/linked-list/P0234PalindromeLinkedList.java) |
| 236 | Lowest Common Ancestor of a Binary Tree / 二叉树的最近公共祖先 | Medium | tree | 后序递归：root 为空或等于 p/q 就直接返回 root；左右结果只有一个非空就返回那个，两个都非空说明 p、q 分居两侧，root 即 LCA | [P0236LowestCommonAncestorOfABinaryTree.java](src/leetcode/tree/P0236LowestCommonAncestorOfABinaryTree.java) |
| 238 | Product of Array Except Self | Medium | array | 前后缀积（left/right 两个数组） | [P0238ProductOfArrayExceptSelf.java](src/leetcode/array/P0238ProductOfArrayExceptSelf.java) |
| 239 | Sliding Window Maximum / 滑动窗口最大值 | Hard | sliding-window | 优先队列存 [数值,下标]，堆顶最大；取堆顶时弹出已滑出窗口的过期元素，O(n log n) | [P0239SlidingWindowMaximum.java](src/leetcode/sliding-window/P0239SlidingWindowMaximum.java) |
| 240 | Search a 2D Matrix II / 搜索二维矩阵 II | Medium | matrix | 左下角标志数：> target 消一行，< target 消一列 | [P0240SearchA2DMatrixII.java](src/leetcode/matrix/P0240SearchA2DMatrixII.java) |
| 279 | Perfect Squares / 完全平方数 | Medium | dp | 完全背包式 DP：f[i] = 1 + min{ f[i - j²] }（j² ≤ i），f[0]=0 作基例；内层枚举「最后一个用的平方数」；O(n√n) 时间 O(n) 空间 | [P0279PerfectSquares.java](src/leetcode/dp/P0279PerfectSquares.java) |
| 283 | Move Zeroes / 移动零 | Easy | two-pointers | 快慢双指针，非零元素依次换到前面，零自动沉底 | [P0283MoveZeroes.java](src/leetcode/two-pointers/P0283MoveZeroes.java) |
| 295 | Find Median from Data Stream / 数据流的中位数 | Hard | heap | 双堆：queMin 大顶堆存较小的一半、queMax 小顶堆存较大的一半；addNum 按大小归堆后，把多出来的一个挪到另一半，保证左堆元素数 >= 右堆且相差不超过 1；findMedian 奇数取左堆顶、偶数取两堆顶平均 | [P0295FindMedianFromDataStream.java](src/leetcode/heap/P0295FindMedianFromDataStream.java) |
| 300 | Longest Increasing Subsequence / 最长递增子序列 | Medium | dp | dp[i] 表示以 nums[i] 结尾的最长严格递增子序列长度，内层枚举前驱 j，nums[j] < nums[i] 时 dp[i] = max(dp[i], dp[j]+1)，答案取全表最大值；O(n²) 时间 O(n) 空间（另有 tails + 二分查找 O(n log n) 写法） | [P0300LongestIncreasingSubsequence.java](src/leetcode/dp/P0300LongestIncreasingSubsequence.java) |
| 322 | Coin Change / 零钱兑换 | Medium | dp | 完全背包 DP：dp[i] 表示凑出金额 i 的最少硬币数，dp[i] = min(dp[i - c]) + 1；用 amount+1 作「不可达」哨兵，全程无需额外判空，最后 dp[amount] > amount 即返回 -1。O(amount × n) 时间 O(amount) 空间 | [P0322CoinChange.java](src/leetcode/dp/P0322CoinChange.java) |
| 347 | Top K Frequent Elements / 前 K 个高频元素 | Medium | heap | 桶排序：先用 HashMap 统计频次，再用「频次」当下标建桶（桶数组长度 n+1），从高频桶往低频桶倒着取，拿满 k 个即止；O(n) 时间，正是题目 follow-up 想要的优于 O(n log n) 的解法 | [P0347TopKFrequentElements.java](src/leetcode/heap/P0347TopKFrequentElements.java) |
| 394 | Decode String / 字符串解码 | Medium | stack | 双栈：一个栈存括号前的重复次数、一个栈存括号前已累积的字符串；遇到 [ 压栈并重置 num/res，遇到 ] 弹栈、把当前 res 重复 n 次再拼回外层；数字是多位所以要 num = num*10 + digit | [P0394DecodeString.java](src/leetcode/stack/P0394DecodeString.java) |
| 416 | Partition Equal Subset Sum / 分割等和子集 | Medium | dp | 01 背包可行性：总和奇数直接 false，否则背包容量 target = sum/2，dp[i] 表示能否凑出和 i，内层倒序遍历容量保证每个数只用一次；O(n × sum) 时间 O(sum) 空间 | [P0416PartitionEqualSubsetSum.java](src/leetcode/dp/P0416PartitionEqualSubsetSum.java) |
| 437 | Path Sum III / 路径总和 III | Medium | tree | 前缀和 + 回溯：根到当前结点的前缀和存进 HashMap，curSum-target 的出现次数就是以当前结点结尾的合法路径数；离开子树时撤销状态，保证只统计祖先 | [P0437PathSumIII.java](src/leetcode/tree/P0437PathSumIII.java) |
| 438 | Find All Anagrams in a String / 找到字符串中所有字母异位词 | Medium | sliding-window | 固定长度滑动窗口 + 26 位计数数组，Arrays.equals 比较 | [P0438FindAllAnagramsInAString.java](src/leetcode/sliding-window/P0438FindAllAnagramsInAString.java) |
| 543 | Diameter of Binary Tree / 二叉树的直径 | Easy | tree | 在 104 的骨架上加一句：每到一个结点就用「左深+右深」更新全局最大直径；空返回 0，叶子返回 1 | [P0543DiameterOfBinaryTree.java](src/leetcode/tree/P0543DiameterOfBinaryTree.java) |
| 560 | Subarray Sum Equals K / 和为 K 的子数组 | Medium | array | 暴力枚举所有子数组，O(n²) | [P0560SubarraySumEqualsK.java](src/leetcode/array/P0560SubarraySumEqualsK.java) |
| 739 | Daily Temperatures / 每日温度 | Medium | stack | 单调栈：栈里存「还没找到更暖一天」的下标，栈底到栈顶温度递减；遇到更暖的温度就 while 弹出并记录 i - prv，没弹出的就留在栈里（答案保持 0） | [P0739DailyTemperatures.java](src/leetcode/stack/P0739DailyTemperatures.java) |
| 763 | Partition Labels / 划分字母区间 | Medium | intervals | 自己的思路：先统计每个字母的总次数，扫描时遇到「本段里首次出现的字母」就把它的总次数加进 flag，每处理一个字符 flag 减 1；flag 归零说明这一段里所有字母的出现都被吸收完了，切一刀 | [P0763PartitionLabels.java](src/leetcode/intervals/P0763PartitionLabels.java) |
| 994 | Rotting Oranges / 腐烂的橘子 | Medium | graph | 多源 BFS 分层：先把所有烂橘子入队，每层 = 1 分钟；把「入队时标记为 2」防止重复入队；while 里的 count>0 保证没有新鲜橘子时直接返回 0 | [P0994RottingOranges.java](src/leetcode/graph/P0994RottingOranges.java) |
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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class P0098ValidateBinarySearchTree {
    // 有效 二叉搜索树定义如下：
    // 节点的左子树只包含 严格小于 当前节点的数。
    // 节点的右子树只包含 严格大于 当前节点的数。
    // 所有左子树和右子树自身必须也是二叉搜索树。
    public boolean isValidBST(TreeNode root) {
        // 调用重载递归函数：初始下界负无穷，上界正无穷
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long lower, long upper) {
        // 基线条件：空节点，直接返回true（空树是合法BST）
        if(root == null) return true;
        // 判断：当前节点不在区间 (lower, upper) → 违反BST规则，返回false
        if(root.val <= lower || root.val >= upper) return false;
        // 左右子树必须同时满足才是合法BST
        return helper(root.left, lower, root.val) && helper(root.right, root.val, upper);
    }
}

//中序遍历 + 判断严格递增，面试可能问
/*
class Solution {
    // 思路：BST 的中序遍历一定是「严格递增」序列（左 < 根 < 右），
    //      所以「验证 BST」等价于「验证中序遍历严格递增」。
    //      这里用显式栈做迭代中序 —— 就是第 94 题那个骨架，只多了一个 prev 记录前一个值。
    //
    // 与上下界传递法（上面的主解）的对比：
    //      上下界法：递归时把 (下界, 上界) 区间往下传，不需要额外变量，代码更短；
    //      中序法：  顺着中序走一遍，只需要一个 prev，更贴近 BST 的定义本身。
    //      两种都要会，面试官常追问「还有别的做法吗」。
    //
    // 注意：
    //  1) prev 必须用 long（Long.MIN_VALUE）。若用 int + Integer.MIN_VALUE，
    //     当第一个结点值恰好是 Integer.MIN_VALUE 时会误判成「不大于 prev」而返回 false；
    //  2) 取消注释后需要在文件顶部补一行  import java.util.*;  （用到 Deque / ArrayDeque）
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stk = new ArrayDeque<>();
        long prev = Long.MIN_VALUE;                 // 记录中序里上一个访问过的值
        while (root != null || !stk.isEmpty()) {
            // 一路向左压栈（中序：先把左子树走到底）
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();                       // 弹出即「访问」当前结点
            if (root.val <= prev) return false;     // 不是严格递增 → 不是 BST（用 <= 顺便挡住重复值）
            prev = root.val;
            root = root.right;                      // 转向右子树
        }
        return true;
    }
}
*/
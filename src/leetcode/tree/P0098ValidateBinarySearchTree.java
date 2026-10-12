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

    public boolean helper(TreeNode root, long lower, long upper) {
        // 基线条件：空节点，直接返回true（空树是合法BST）
        if(root == null) return true;
        // 判断：当前节点不在区间 (lower, upper) → 违反BST规则，返回false
        if(root.val <= lower || root.val >= upper) return false;
        // 左右子树必须同时满足才是合法BST
        return helper(root.left, lower, root.val) && helper(root.right, root.val, upper);
    }
}
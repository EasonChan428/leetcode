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
class P0543DiameterOfBinaryTree {
    public int maxlen = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        //计算所有节点的左右子树的最大深度之和，然后比较
        maxDepth(root); // 递归遍历整棵树，更新maxlen
        return maxlen;
    }

    public int maxDepth(TreeNode root){
        if(root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        // 核心：当前节点作为路径最高点，路径长=左深+右深，和全局maxlen比较，保留更大的值
        maxlen = Math.max(maxlen, leftDepth + rightDepth);
        // 返回当前子树的最大深度：左右较大深度 + 当前节点这一层
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
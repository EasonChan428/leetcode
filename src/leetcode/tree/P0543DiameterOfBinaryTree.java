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
    private int maxlen = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxlen = 0; // 进函数先归零：字段的值会跨调用保留，不重置的话同一个对象第二次调用会拿到上一次的旧结果
        //计算所有节点的左右子树的最大深度之和，然后比较
        maxDepth(root); // 递归遍历整棵树，更新maxlen
        return maxlen;
    }

    private int maxDepth(TreeNode root){
        if(root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        // 核心：当前节点作为路径最高点，路径长=左深+右深，和全局maxlen比较，保留更大的值
        maxlen = Math.max(maxlen, leftDepth + rightDepth); //本代码返回的不是节点计数，是边的数量，所以leftDepth和rightDepth直接相加
        // 返回当前子树的最大深度：左右较大深度 + 当前节点这一层
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
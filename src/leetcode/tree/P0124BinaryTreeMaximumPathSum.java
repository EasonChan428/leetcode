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
class P0124BinaryTreeMaximumPathSum {
    // maxSum全局变量：记录当前子树内部的分叉路径最大值（可以同时取左 + 当前 + 右，这个路径不能向上传给父节点，只用来更新答案）
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE; // 进函数先归零（注意是 MIN_VALUE 不是 0，否则全负树会算成 0）：字段值会跨调用保留，不重置的话同一个对象第二次调用会拿到上一次的旧结果
        helper(root);
        return maxSum;
    }
    
    private int helper(TreeNode root) {
        if(root == null) return 0;
        // 如果子树贡献是负数，直接取0，代表【不选这个子分支】,所以要增加一个max判断
        int left = Math.max(0, helper(root.left)); 
        int right = Math.max(0, helper(root.right));
        maxSum = Math.max(maxSum, left + right + root.val);
        // 返回给上层：只能选左边或者右边中更大的一支，加上当前节点，只能走单边，不能分叉
        return root.val + Math.max(left, right);
    }
}
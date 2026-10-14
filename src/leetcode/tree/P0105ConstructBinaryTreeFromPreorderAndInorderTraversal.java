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
class P0105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode helper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        // preorder 为空，直接返回 null
        if(p_start == p_end) return null;
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找到根节点的位置
        int i_root = 0;
        for(int i = i_start; i < i_end; i++){
            if(root_val == inorder[i]) {
                i_root = i;
                break;
            }
        }
        int newNum = i_root - i_start;
        //递归的构造左子树
        root.left = helper(preorder, p_start + 1, p_start + newNum + 1, inorder, i_start, i_root);
        //递归的构造右子树
        root.right = helper(preorder, p_start + newNum + 1, p_end, inorder, i_root + 1, i_end);
        return root;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class P0236LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 根据定义，若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：
        // p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
        // p=root ，且 q 在 root 的左或右子树中；
        // q=root ，且 p 在 root 的左或右子树中；
        if(root == null || root == p || root == q){
            //只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
            return root;
        }
        //根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //p和q都没找到，那就没有
        if(left == null && right == null) return null;
        //左子树没有p也没有q，就返回右子树的结果
        if(left == null) return right;
        //右子树没有p也没有q就返回左子树的结果
        if(right == null) return left;
        //左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
        return root;
    }
}
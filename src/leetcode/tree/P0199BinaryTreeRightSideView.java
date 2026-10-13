import java.util.*;

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

class P0199BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        // DFS递归,先递归右子树，再递归左子树，当某个深度首次到达时，对应的节点就在右视图中
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans); // 初始进入递归深度为0
        return ans;
    }

    private void dfs(TreeNode root, int depth, List<Integer> ans) {
        if(root == null) return;
        if(depth == ans.size()) ans.add(root.val); // 这个深度首次遇到
        dfs(root.right, depth + 1, ans); // 先递归右子树，保证首次遇到的一定是最右边的节点
        dfs(root.left, depth + 1, ans);
    }
}

 /*
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // 层序遍历
        List<Integer> ans = new ArrayList<>();
        List<TreeNode> cur = new ArrayList<>();
        if(root == null) return ans;
        cur.add(root);

        while(!cur.isEmpty()) {
            // 把每一层的最后一个节点值保存到答案中
            ans.add(cur.getLast().val);
            List<TreeNode> level = new ArrayList<>();
            for(TreeNode node : cur) {
                if(node.left != null) level.add(node.left);
                if(node.right != null) level.add(node.right);
            }
            // cur移动到下一层
            cur = level;
        }
        return ans;
    }
}
*/
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
class P0102BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //两个场景：「层序遍历」、「最短路径」 用 BFS 遍历
        /*
        //BFS 遍历使用队列数据结构：
        void bfs(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // Java 的 pop 写作 poll()
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
          }
        }
        */
        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root != null) {
           queue.add(root);
        }
        while(!queue.isEmpty()){
            //在每一层遍历开始前，先记录队列中的结点数量 n（也就是这一层的结点数量），然后一口气处理完这一层的 n 个结点
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < n; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            ans.add(level);
        }
        return ans;
    }
}
/*
//递归方法（dfs）：
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(0, root, res);
        return res;
    }

    private void dfs(int index, TreeNode root, List<List<Integer>> res) {
        if (res.size() == index)
            res.add(new ArrayList<>());

        res.get(index).add(root.val);
        if (root.left != null)
            dfs(index + 1, root.left, res);
        if (root.right != null)
            dfs(index + 1, root.right, res);
    }
}
*/
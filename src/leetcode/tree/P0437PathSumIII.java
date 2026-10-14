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
class P0437PathSumIII {
    // 前缀和解法(较难理解 要背！)
    // 两节点间的路径和 = 两节点的前缀和之差
    // HashMap的key是前缀和， value是该前缀和的节点数量，记录数量是因为有出现复数路径的可能
    // 当我们讨论两个节点的前缀和差值时有一个前提：一个节点必须是另一个节点的祖先节点，所以状态恢复代码的作用就是：在遍历完一个节点的所有子节点后，将其从map中除去，保证一个节点的前缀和信息只对其子节点们有效

    Map<Long, Integer> mem = new HashMap<Long, Integer>(); // 保存前缀树
    int target;
    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        mem.put(0L, 1); //前缀树为0的个数至少是一个(在根节点之前，虚拟存在一个前缀和等于0的位置作为哨兵)
        return dfs(root, 0L);
    }
    private int dfs(TreeNode root, Long curSum) {
        if(root == null) return 0;
        curSum += root.val; // 得到当前前缀树的值
        int res = 0;
        // 得到我们想要前缀树的个数，想要前缀树值就是当前前缀树值减去目标值
        res += mem.getOrDefault(curSum - target, 0); 
        // 将当前前缀树的值保存
        mem.put(curSum, mem.getOrDefault(curSum, 0) + 1);
        int left = dfs(root.left, curSum);
        int right = dfs(root.right, curSum);
        mem.put(curSum, mem.get(curSum) - 1); // 状态恢复代码
        // 结果是当前节点前缀树的个数加上左边满足的个数加右边满足的个数
        return res + left + right;
    }
}
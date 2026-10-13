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
class P0230KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        //使用迭代方法可以在找到答案后停止，不需要遍历整棵树
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(root != null || !stack.isEmpty()) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if(k == 0) break;
            root = root.right;
        }
        return root.val;
    }
}

//如果你需要频繁地查找第 k 小的值，你将如何优化算法？

// 可以记录下以每个节点为根节点的子树的节点数，并在查找第 k 小的值时，使用如下方法搜索：
// 令 node 等于根节点，开始搜索。
// 对当前节点 node 进行如下操作：
// 如果 node 的左子树的节点数 left 小于 k−1，则第 k 小的元素一定在 node 的右子树中，令 node 等于其的右子节点，k 等于 k−left−1，并继续搜索；
// 如果 node 的左子树的节点数 left 等于 k−1，则第 k 小的元素即为 node ，结束搜索并返回 node 即可；
// 如果 node 的左子树的节点数 left 大于 k−1，则第 k 小的元素一定在 node 的左子树中，令 node 等于其左子节点，并继续搜索。
// 在实现中，我们既可以将以每个节点为根节点的子树的节点数存储在节点中，也可以将其记录在哈希表中

/*
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        MyBst bst = new MyBst(root);
        return bst.kthSmallest(k);
    }
}

class MyBst {
    TreeNode root;
    Map<TreeNode, Integer> nodeNum;

    public MyBst(TreeNode root) {
        this.root = root;
        this.nodeNum = new HashMap<TreeNode, Integer>();
        countNodeNum(root);
    }

    // 返回二叉搜索树中第k小的元素
    public int kthSmallest(int k) {
        TreeNode node = root;
        while (node != null) {
            int left = getNodeNum(node.left);
            if (left < k - 1) {
                node = node.right;
                k -= left + 1;
            } else if (left == k - 1) {
                break;
            } else {
                node = node.left;
            }
        }
        return node.val;
    }

    // 统计以node为根节点的子树的节点数
    private int countNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        nodeNum.put(node, 1 + countNodeNum(node.left) + countNodeNum(node.right));
        return nodeNum.get(node);
    }

    // 获取以node为根节点的子树的节点数
    private int getNodeNum(TreeNode node) {
        return nodeNum.getOrDefault(node, 0);
    }
}
*/
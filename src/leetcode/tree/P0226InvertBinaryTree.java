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
class P0226InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.left = rightNode;
        root.right = leftNode;
        return root;
    }
}

//迭代法（DFS + 显式栈）—— 面试可能会问
/*
class Solution {
    // 思路：递归版是靠「系统调用栈」自动帮我们记住"还有哪些结点没处理"；
    //      这里用一个自己 new 出来的栈，把这件事「显式」地做一遍 ——
    //      每弹出一个结点，就先交换它的左右孩子（= 递归版里对当前结点做的操作），
    //      再把两个非空孩子压栈（= 递归版里"继续处理左右子树"）。
    //
    // 与递归版的对应关系：
    //      invertTree(root.left) / invertTree(root.right)  →  压栈
    //      交换 root.left 和 root.right                    →  弹出后交换
    //      函数返回（回到上一层）                          →  继续 while 弹出下一个
    //
    // 复杂度：时间 O(n) —— 每个结点入栈一次、出栈一次；
    //        空间 —— 栈最深时可能同时排着多个待处理的同层结点，
    //                平衡树约 O(log n)，退化形状（如"毛毛虫"树）最坏 O(n)。
    //
    // 为什么要知道这个版本：递归版的空间是系统调用栈，树非常深时可能抛 StackOverflowError；
    //      显式栈用的是堆内存，不受递归深度限制。本题 n <= 100，递归版完全够用，
    //      但面试官常追问"能不能不用递归"，这个版本就是标准答案。
    //      （另一种等价写法是 BFS + 队列，思路一样，只是把"一路深入"换成"一层层处理"。）
    //
    // 注意：取消注释后需要在文件顶部补一行  import java.util.*;  （用到 Deque / ArrayDeque）
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // 用 Deque 当栈（push / pop / isEmpty）。
        // 接口选 Deque 而不是遗留类 Stack；实现类选 ArrayDeque：
        // 数组实现，比 LinkedList 更快、更省内存。
        Deque<TreeNode> stk = new ArrayDeque<>();
        stk.push(root);

        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();     // 取出一个待处理的结点

            // ① 交换它的左右孩子。
            //    必须用临时变量 tmp 暂存左孩子 —— 否则第一行赋值就把左孩子覆盖了，
            //    第二行拿到的已经是"新的左孩子"（原来的右孩子），左子树整支丢失。
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            // ② 把两个非空孩子压栈，等会儿再各自处理。
            //    压栈的先后顺序无所谓：不管谁先进，两个孩子最终都会被弹出，
            //    并在弹出时各自完成自己的交换，结果与顺序无关。
            if (node.left != null) stk.push(node.left);
            if (node.right != null) stk.push(node.right);
        }

        return root;
    }
}
*/

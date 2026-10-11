/**
 * LeetCode 二叉树题共用的节点定义。
 *
 * 和 ListNode / Node 同理：LeetCode 把定义放在每道题的注释里，复制出来的代码
 * 不含可编译的定义，直接存盘会报「找不到符号: 类 TreeNode」；而默认包（没有
 * package 声明）里不能重复声明同名类，所以树节点统一放 common/ 这一份，
 * 各题文件里不要再各写一份。
 *
 * 构造函数取 LeetCode 现行定义的三个（无参 / 单参 / 三参），
 * 这样代码按哪个版本写都能编译。
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

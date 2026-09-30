/**
 * LeetCode 链表题共用的单链表节点定义。
 *
 * 为什么需要这个文件：
 *   LeetCode 编辑器把 ListNode 的定义放在每道题代码的注释里，复制出来的代码
 *   并不包含可编译的定义，直接存盘会报「找不到符号: 类 ListNode」。
 *   而默认包（没有 package 声明）里多个文件不能重复声明同名类，
 *   所以链表题的节点定义统一放这一份，各题文件只管用，不要各写一份。
 *
 * 不加 public：默认包里不加 public 也能被同包（即所有无 package 的文件）访问，
 * 这样文件名和类名的耦合更松。树节点可照此再加一个 TreeNode.java。
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

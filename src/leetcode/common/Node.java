/**
 * LeetCode 带随机指针的链表题共用节点定义（第 138 题「随机链表的复制」）。
 *
 * 和 ListNode 同理：LeetCode 把定义放在每道题的注释里，复制出来的代码不含
 * 可编译的定义，直接存盘会报「找不到符号: 类 Node」；而默认包（没有 package
 * 声明）里不能重复声明同名类，所以这类节点统一放 common/ 这一份。
 *
 * ⚠ 注意：LeetCode 里 Node 这个名字被多种结构复用，定义并不相同 ——
 *     138 随机链表:      val / next / random          ← 本文件
 *     133 克隆图:        val / neighbors
 *     116 填充右侧指针:  val / left / right / next
 *     559 N 叉树:        val / children
 *   写到上面那些题时字段对不上，需要把本文件扩成「字段并集」或给那道题换类型名，
 *   到时候再处理（别直接复制 LeetCode 注释里的定义进来，会撞名）。
 */
class Node {
    int val;
    Node next;
    Node random;

    Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

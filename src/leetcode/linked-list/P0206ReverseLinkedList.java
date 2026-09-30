/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class P0206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        //迭代
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode n = cur.next;
            cur.next = pre;
            pre = cur;
            cur = n;
        }
        return pre; //pre移动到原链表最末端变为表头
    }
}
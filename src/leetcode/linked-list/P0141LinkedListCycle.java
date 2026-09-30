/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class P0141LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        //链表问题优先考虑双指针！
        if(head == null || head.next == null) return false;
        //循环条件一定是判断快慢指针是否重合,所以不能同时放在head
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
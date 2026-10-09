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
class P0024SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        ListNode ans = new ListNode(0,head);
        ListNode fast = head;
        ListNode slow = ans;
        while(fast != null && fast.next != null){
            ListNode nextPair = fast.next.next; //重要！提前保存下一组起点
            slow.next = fast.next;
            fast.next.next = fast;
            fast.next = nextPair;
            slow = fast; // slow移动到fast，作为下一组的前驱
            fast = fast.next; // fast直接跳到下一组
        }
        return ans.next;
    }
}
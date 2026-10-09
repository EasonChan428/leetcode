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
class P0019RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int sz = 0;
        ListNode l1 = head;
        while(l1 != null){
            sz++;
            l1 = l1.next;
        }
        if(sz == 1) return null;
        if(sz == n) return head.next;
        ListNode l2 = head,l3 = head;
        for(int i = 0; i < sz - n; i++){
            l3 = l2;
            l2 = l2.next;
        }
        l3.next = l2.next;
        return head;
    }
}
//只用遍历一次链表的双指针法，设置虚指针dummy = new ListNode(0,head); 让slow=dummy,fast=head,随后fast比slow多走n步，slow即可停在倒数第n个前，slow.next = slow.next.next; （最优解！）
/*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
*/
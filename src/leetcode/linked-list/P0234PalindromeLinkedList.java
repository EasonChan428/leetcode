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
class P0234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        //最优思路：快慢指针找中点 + 反转后半段链表，原地判断，空间 O (1)，时间 O (n)
        if(head == null) return true;
        ListNode slow = head;
        ListNode fast = head;

        // 1. 快慢指针：slow 一次走 1 步，fast 一次走 2 步，fast 走到末尾时 slow 刚好到链表中点
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转 slow 后面的后半部分链表
        ListNode half = reverseList(slow);
        ListNode p1 = head;
        ListNode p2 = half;
        boolean ans =true;

        // 3. 双指针从头、尾向中间比对节点值，全部相等就是回文
        while(p2 != null){
            if(p1.val != p2.val){
                ans = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        
        // 4. （可选）把后半段链表恢复原状，不破坏原链表
        reverseList(half);
        return ans;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode n = cur.next;
            cur.next = pre;
            pre = cur;
            cur = n;
        }
        return pre;
    }
}
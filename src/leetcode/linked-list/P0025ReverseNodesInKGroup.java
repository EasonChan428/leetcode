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
class P0025ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ans = new ListNode(0,head);
        //pre指每次要翻转的链表的头结点的上一个节点,end指每次要翻转的链表的尾节点
        ListNode pre = ans;
        ListNode end = ans;

        while(end.next != null){
            for(int i =0; i < k && end != null; i++){
                //这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常
                end = end.next;
            }
            if(end == null) break;
            //记录下要翻转链表的头节点
            ListNode start = pre.next;
            //先记录下end.next,方便后面链接链表
            ListNode t = end.next;
            //然后断开链表
            end.next = null;
            //进行k个数的翻转
            pre.next = reverse(start);
            //连接链表
            start.next = t;
            //重置pre和end到下一段k的上一个结点
            pre = start;
            end = pre;
        }
        return ans.next;
    }

    private ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }
}
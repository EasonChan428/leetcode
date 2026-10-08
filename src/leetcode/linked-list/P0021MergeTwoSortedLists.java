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
class P0021MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //迭代法
        ListNode head3 = new ListNode();
        ListNode list3 = head3;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                list3.next = list1;
                list1 = list1.next; 
            }else{
                list3.next = list2;
                list2 = list2.next;
            }
            list3 = list3.next;
        }
        if(list1 == null){
            list3.next = list2;
        }else{
            list3.next = list1;
        }
        return head3.next; //重要：返回哑节点的next，不是head3本身！
    }
}
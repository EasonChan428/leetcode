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
class P0148SortList {
    public ListNode sortList(ListNode head) {
        //使用自底向上的方法实现归并排序，则可以达到 O(1) 的空间复杂度
        if(head == null) return null;
        int length = 0;
        ListNode count = head;
        while(count != null){
            length ++;
            count = count.next;
        }
        ListNode dummy = new ListNode(0,head);
        //用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1,重复排序合并后加倍
        for(int subLength = 1; subLength < length; subLength *= 2){
            ListNode pre = dummy;
            ListNode cur = dummy.next;//注意：这里不能写成head，dummy.next 才永远指向当前这一轮链表的真实头部，而head一直指向最初第一个结点
            while(cur != null){
                ListNode head1 = cur;
                for(int i = 1; i < subLength && cur.next != null; i++){
                    cur = cur.next;
                }
                ListNode head2 = cur.next;
                cur.next = null;
                cur = head2;
                for(int i = 1; i < subLength && cur != null && cur.next != null; i++){ //注意！边界判断不同
                    cur = cur.next;
                }
                ListNode next = null;
                if(cur != null){
                    next = cur.next; //存储下一段开头
                    cur.next = null;
                }
                // 断开两段子链表后进行合并排序
                ListNode merged = merge(head1,head2);
                pre.next = merged;
                while(pre.next != null){
                    pre = pre.next;
                }
                cur = next;
            }
        }
        return dummy.next;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode head3 = new ListNode(0);
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
        return head3.next;
    }
}
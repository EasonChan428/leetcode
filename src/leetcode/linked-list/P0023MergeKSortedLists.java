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

class P0023MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        //分治合并法，与排序链表相似。时间复杂度为 O(N logk)，空间复杂度：递归会使用到 O(logk) 空间代价的栈空间(时间上最优解！递归合并)
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r){
        if(l == r) return lists[l];
        if(l > r) return null;

        int mid = (l + r) / 2;
        // 左半部分合并结果 和 右半部分合并结果，再调用mergeTwoLists合并两个有序链表
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
        return head3.next;
    }
}
/*
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //最简单直白方法：顺序归并。时间复杂度为 O(k^2*n),消耗时间较多，空间复杂度：没有用到与 k 和 n 规模相关的辅助空间，故渐进空间复杂度为 O(1)
        ListNode ans = null;
        for(int i = 0; i < lists.length; i++){
            ans = mergeTwoLists(ans,lists[i]);
        }
        return ans;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
        return head3.next;
    }
}
*/
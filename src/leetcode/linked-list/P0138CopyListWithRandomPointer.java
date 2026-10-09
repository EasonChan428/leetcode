/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class P0138CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        //思路：拼接 + 拆分
        //构建 原节点 1 -> 新节点 1 -> 原节点 2 -> 新节点 2 -> …… 的拼接链表，如此便可在访问原节点的 random 指向节点的同时找到新对应新节点的 random 指向节点。
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null){
            Node t = new Node(cur.val);
            t.next = cur.next;
            cur.next = t;
            cur = t.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null){
            if(cur.random != null){
                //必需！否则 null.next 直接 NPE
                cur.next.random = cur.random.next; //该思路最核心的一句代码！
            }
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head;
        Node ans = head.next;
        while(cur.next != null){
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return ans; // 返回新链表头节点
    }
}
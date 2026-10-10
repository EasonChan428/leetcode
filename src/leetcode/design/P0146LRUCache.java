import java.util.*;

class P0146LRUCache {
    //LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对
    //首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1) 的时间内完成 get 或者 put 操作，在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在
    class DLinkNode {
        int key;
        int value;
        DLinkNode prev;
        DLinkNode next;
        public DLinkNode() {}
        public DLinkNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private Map<Integer, DLinkNode> cache = new HashMap<Integer,DLinkNode>();
    private int size;
    private int capacity;
    private DLinkNode head, tail;

    //LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    public P0146LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }
    //int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 
    public int get(int key) {
        DLinkNode node = cache.get(key);
        if(node == null) return -1;
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }
    //void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字
    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if(node == null){// 如果 key 不存在，创建一个新的节点
            DLinkNode newNode = new DLinkNode(key,value);
            cache.put(key,newNode);
            addToHead(newNode);
            size++;
            if(size > capacity){
                DLinkNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        }else{// 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkNode node){
        removeNode(node);
        addToHead(node);
    }

    private DLinkNode removeTail(){
        DLinkNode res = tail.prev;
        removeNode(res);
        return res; // 因为要删除哈希表中对应的项，所以要返回被移除的结点
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
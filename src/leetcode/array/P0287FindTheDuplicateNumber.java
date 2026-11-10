class P0287FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        // 数组中有一个重复的整数 <==> 链表中存在环
        // 找到数组中的重复整数 <==> 找到链表的环入口
        // 142 题中慢指针走一步 slow = slow.next ==> 本题 slow = nums[slow]
        // 142 题中快指针走两步 fast = fast.next.next ==> 本题 fast = nums[nums[fast]]
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        int res = 0;
        int ptr = slow;
        while(res != ptr) {
            res = nums[res];
            ptr = nums[ptr];
        }

        return res;
    }
}
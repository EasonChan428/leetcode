class P0041FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 第一轮：原地哈希，数字x放到下标x-1
        for(int i = 0; i < n; i++){
            //当前数字nums[i]是1~n之间的有效正数，并且它不在正确的位置，就交换（第三个条件防重复）
            while(nums[i] >= 1 && nums[i] <= n && nums[nums[i]-1] != nums[i]){
                int t = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = t;
            }//为什么是 while 不是 if？交换完成后，i 位置会得到一个新数字，这个新数字也要放到正确位置，需要循环处理，if 只能交换一次。
        }
        //第二轮：扫描数组，找到第一个不满足 nums[i] = i+1 的下标
        for(int i =0; i < n; i++){
            if(nums[i] != i+1){
                return i+1;
            }
        }
        return n+1;
    }
}
class P0053MaximumSubarray {
    public int maxSubArray(int[] nums) {
        //考虑 nums[i] 单独成为一段还是加入 f(i−1) 对应的那一段，这取决于 nums[i] 和 f(i−1)+nums[i] 的大小，我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：f(i)=max{f(i−1)+nums[i],nums[i]}
        int f=0;
        int ans=nums[0];
        for(int i=0;i<nums.length;i++){
            f=Math.max(f+nums[i],nums[i]);
            if(f>ans) ans=f;
        }
        return ans;
    }
}
class P0152MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 本题DP差异化：分别维护一个最大和最小dp数组，为负负得正做准备
        int[] maxDP = new int[n];
        int[] minDP = new int[n];
        int res = nums[0];
        maxDP[0] = nums[0];
        minDP[0] = nums[0];

        for(int i = 1; i < n; i++) {
            // 三种可能的值 
            int t1 = nums[i];
            int t2 = maxDP[i - 1] * nums[i];
            int t3 = minDP[i - 1] * nums[i];
            // 更新最大和最小乘积的两个数组
            maxDP[i] = Math.max(t1, Math.max(t2, t3));
            minDP[i] = Math.min(t1, Math.min(t2, t3));
            // 更新答案，记录最大值
            res = Math.max(res, maxDP[i]);
        }
        return res;
    }
}
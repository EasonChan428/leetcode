class P0198HouseRobber {
    public int rob(int[] nums) {
        // 本题找出状态转移方程即可
        // 对于每间房子，都有两种状态，偷/不偷：
        // 偷：前n个房间最高金额=前n-2个房间最高金额+第n个房间金额
        // 不偷：前n个房间最高金额=前n-1个房间最高金额
        int n = nums.length;
        if(n == 1) return nums[0];
        int[] maxN = new int[n];
        maxN[0] = nums[0];
        maxN[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < n; i++) {
            // 两者取最大就可以得出前n个房间最高金额
            maxN[i] = Math.max(maxN[i - 1], maxN[i - 2] + nums[i]);
        }
        return maxN[n - 1];
    }
}
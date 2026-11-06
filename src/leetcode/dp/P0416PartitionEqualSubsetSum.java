class P0416PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // 1. 计算数组总和
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        // 2. 总和是奇数，不可能平分，直接返回false
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        // 3. 初始化dp数组，dp[0]=true
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        // 4. 遍历每个数字
        for(int num : nums) {
            // 倒序遍历背包容量，01背包标准写法
            for(int i = target; i >= num; i--) {
                // 如果i-num能凑出来，那i也能凑出来
                if(dp[i - num]) {
                    dp[i] = true;
                }
            }
        }
        // 5. 看能不能刚好凑出target
        return dp[target];
    }
}
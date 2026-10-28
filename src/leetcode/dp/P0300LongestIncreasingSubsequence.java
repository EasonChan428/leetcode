import java.util.*;

class P0300LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        /*
        转移方程： 设 j∈[0,i)，考虑每轮计算新 dp[i] 时，遍历 [0,i) 列表区间，做以下判断：
        当 nums[i]>nums[j] 时： nums[i] 可以接在 nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j]+1 ；
        当 nums[i]<=nums[j] 时： nums[i] 无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。
        上述所有 1. 情况 下计算出的 dp[j]+1 的最大值，为直到 i 的最长上升子序列长度（即 dp[i] ）。实现方式为遍历 j 时，每轮执行 dp[i]=max(dp[i],dp[j]+1)。
        */
        int n = nums.length;
        if(n == 1) return 1;
        int[] dp = new int[n]; // dp[i] 的值代表 nums 以 nums[i] 结尾的最长子序列长度
        int res = 0;
        Arrays.fill(dp, 1); // 每个元素都至少可以单独成为子序列，此时长度都为 1
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]); // 记录 dp 列表最大值，即可得到全局最长上升子序列长度
        }
        return res;
    }
}
// 第二次看要学会动态规划 + 二分查找的方法
import java.util.*;

class P0322CoinChange {
    // 按照爬楼梯的思路来想，amount为目标台阶，每一步只能走coins数组中的台阶数，dp[i]表示到达第i级台阶所需的最小步数，那么自然可以想到dp[i] = min( dp[ i-coins[j] ] )+1，其中i - coins[j] >= 0 且j∈[ 0, len(coins) ]。如果i - coins[j] < 0说明走不到目标台阶i，会超出台阶i。

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        // 定义dp数组，长度为 amount+1，覆盖金额 0~amount
        int[] dp = new int[amount + 1];
        // 将数组所有位置初始化为最大值，表示“初始无法凑出”
        Arrays.fill(dp, max);
        // 基准情况：金额0需要0枚硬币
        dp[0] = 0;

        // 外层循环：逐个计算 1~amount 每个金额的最少硬币数
        for (int i = 1; i <= amount; i++) {
            // 内层循环：遍历每一种硬币，尝试用它来更新当前金额的最优解
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) { // 当前金额足够使用这枚硬币
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        
        // 最终判断：如果目标金额仍是最大值，说明无法凑出，返回-1；否则返回最少硬币数
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
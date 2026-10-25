class P0121BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        // 动态规划 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
        int max = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            } else {
                max = Math.max(max, prices[i] - min);
            }
        }
        return max;
    }
}
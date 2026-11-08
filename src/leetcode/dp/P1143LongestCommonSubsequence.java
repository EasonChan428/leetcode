class P1143LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // 易错点
        // 1. 下标错位：dp[i][j]对应字符串 i-1、j-1 位置，直接写charAt(i)会越界；
        // 2. 相等情况的转移：匹配时必须用 dp[i-1][j-1]+1，不能用 max(dp[i-1][j], dp[i][j-1])+1，会重复计数；
        // 3. 循环边界：i 从 1 到 m，j 从 1 到 n，不要从 0 开始，否则会访问 dp [-1] 越界；
        // 4. 子序列和子串区分：子序列不要求连续，所以不匹配时取左右最大值；如果是连续子串，不匹配时直接置 0，逻辑不同。
        int m = text1.length();
        int n = text2.length();
        // dp[i][j] 的含义：text1 的前 i 个字符（下标 0~i-1）和 text2 的前 j 个字符（下标 0~j-1）的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1]; // Java 中 int 数组默认值就是 0，所以不需要手动初始化第 0 行第 0 列，天然满足边界条件

        for(int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for(int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                // 两个字符相同，说明这个字符可以加入公共子序列
                if(c1 == c2) {
                    // 两个字符串都去掉这个字符后的LCS长度dp[i-1][j-1]再加上当前这 1 个匹配的字符，就是当前前缀的 LCS 长度
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 字符不匹配，当前字符无法同时加入公共子序列，有两种舍弃方案：
                    // 舍弃 text1 的第 i 个字符，看 dp[i-1][j]  舍弃 text2 的第 j 个字符，看 dp[i][j-1]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
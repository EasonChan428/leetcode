class P0072EditDistance {
    public int minDistance(String word1, String word2) {
        // word1[0..i] 变换成 word2[0..j] 主要有三种手段
        // 使用 k 步，把 word1[0..i-1] 变换到 word2[0..j-1]，消耗 k 步。再把 word1[i] 改成 word2[j]，就行了。如果 word1[i] == word2[j]，什么也不用做，一共消耗 k 步，否则需要修改，一共消耗 k + 1 步。
        // 经过 k 步，把 word1[0..i-1] 变换到 word2[0..j]，消耗掉 k 步，再把 word1[i] 删除，这样，word1[0..i] 就完全变成了 word2[0..j] 了。一共 k + 1 步。
        // 经过 k 步，把 word1[0..i] 变换成 word2[0..j-1]，消耗掉 k 步，接下来，再插入一个字符 word2[j], word1[0..i] 就完全变成了 word2[0..j] 了。
        int m = word1.length();
        int n = word2.length();

        //dp数组表示前i个word1变换成前j个word2需要的最少步骤
        int[][] dp = new int[m + 1][n + 1];

        // 边界初始化
        for(int i = 0; i <= m; i++) dp[i][0] = i;
        for(int j = 0; j <= n; j++) dp[0][j] = j;

        for(int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for(int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if(c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 易错点：这里一定要记得全部比较三种路径，因为三种路径的word1[0..i]变换成word2[0..j]都只要一步
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
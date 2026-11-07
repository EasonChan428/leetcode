class P0005LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n < 2) return s;

        // 表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false
        boolean[][] dp = new boolean[n][n];

        int start = 0; // 最长回文起点
        int maxlen = 1; // 最长回文长度

        // 枚举右端点 j
        for(int j = 0; j < n; j++) {
            // 枚举左端点 i
            for(int i = 0; i <= j; i++) {
                // 判断 dp[i][j]
                if(s.charAt(i) == s.charAt(j)) {
                    //左右端点之间的数字个数小于等于1或者中间本身就是回文数
                    if(j - i <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        int len = j - i + 1;
                        //更新最长回文长度和起点
                        if(maxlen < len) {
                            maxlen = len;
                            start = i;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + maxlen);
    }
}
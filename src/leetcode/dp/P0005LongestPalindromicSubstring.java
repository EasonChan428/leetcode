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

// 【可选解法二】中心扩展：O(n²) 时间、O(1) 额外空间，代码最短，面试最常被点名问的那个做法
// 思路一句话：任何回文串都有中心，从中心往两边同时走，能走多远就是多长
// 回文只有两种长度：奇数（中心是某个字符）和偶数（中心在两个字符之间），所以中心共 2n-1 个
// 下面注释块里的类名是 class Solution，取消注释即可单独提交（和外层 P0005LongestPalindromicSubstring 不重名）
/*
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        // 空串和单字符直接返回：否则会走进下面的循环，而 maxlen=1 对空串不成立（会 substring 越界）
        if (n < 2) return s;
        int start = 0;   // 最长回文的起点
        int maxlen = 1;  // 最长回文长度，最短的答案就是单个字符

        // 把「中心」压成一维编号 c，一共 2n-1 个：
        //   c = 0,1,2,... 偶数 c 表示「中心就是第 c/2 个字符」-> 扩出来的是奇数长度回文（如 "aba"）
        //   奇数 c 表示「中心在第 c/2 和第 c/2+1 个字符之间」-> 扩出来的是偶数长度回文（如 "abba"）
        for (int c = 0; c < 2 * n - 1; c++) {
            int l = c / 2;          // 中心左端
            int r = l + c % 2;      // c 为偶数时 r == l（同一个字符）；c 为奇数时 r == l+1（夹缝）
            // 只要左右两个字符相等就继续往外扩，越界或不等就停
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            // 关键换算：退出循环时 (l, r) 已经是一对「不合法」的指针（越界或字符不等）
            // 所以真正的回文区间是 (l+1, r-1)，长度 = (r-1) - (l+1) + 1 = r - l - 1
            int len = r - l - 1;
            if (len > maxlen) {
                maxlen = len;
                start = l + 1;      // 起点同理是 l+1
            }
        }
        return s.substring(start, start + maxlen);
    }
}
*/
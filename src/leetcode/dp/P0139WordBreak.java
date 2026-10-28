import java.util.*;

class P0139WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 本题注意记得使用哈希Set和substring函数即可
        Set<String> dict = new HashSet<String>(wordDict); // O(1) 查词
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空串可拆分

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) { // 枚举分割点 j
                if(dp[j] && dict.contains(s.substring(j, i))) {
                    // 只要前半段可拆分 dp[j] == true 且后半段是字典里的单词 dict.contains(...)，那么前 i 个字符就可拆分
                    dp[i] = true;
                    // 到一个可行分割就break，不必找全部分法
                    break;
                }
            }
        }
        return dp[n];
    }
}
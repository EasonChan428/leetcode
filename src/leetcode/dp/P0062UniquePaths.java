import java.util.*;

class P0062UniquePaths {
    public int uniquePaths(int m, int n) {
        // 动态规划
        int[][] dp = new int[m][n]; // 令 dp[i][j] 是到达 i, j 最多路径

        // 注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1
        for(int i = 0; i < n; i++) dp[0][i] = 1;
        for(int i = 0; i < m; i++) dp[i][0] = 1;

        // 每次只能向下或者向右移动一步,因此动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

/*空间复杂度 O(n)优化
class Solution {
    public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }
}
*/
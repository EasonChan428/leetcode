import java.util.*;

class P0131PalindromePartitioning {
    boolean[][] flag; // 表示 s[i..j] 是否为回文串
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;
    // 回溯 + 动态规划预处理
    public List<List<String>> partition(String s) {
        res.clear();
        ans.clear();
        n = s.length();
        flag = new boolean[n][n];
        // 单个字符一定是回文：f[i][i]=true，所以先全部填 true
        for(int i = 0; i < n; i++) {
            Arrays.fill(flag[i], true); // 先把整行全部填成 true，后续循环会把不是回文的位置覆盖改成 false
        }
        // DP预处理，从后往前填f数组
        for(int i = n - 1; i >= 0; i--) { // f[i][j]的值依赖[i+1][j-1]（i更大，j更小），必须先算i更大的行，才能算当前i
            for(int j = i + 1; j < n; j++) {
                // i,j字符相等，并且中间i+1~j-1也是回文，则i~j是回文
                flag[i][j] = (s.charAt(i) == s.charAt(j)) && flag[i + 1][j - 1];
            }
        }
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int i) {
        // 终止条件：i走到字符串末尾，代表找到一组合法分割
        if(i == n) {
            res.add(new ArrayList<String>(ans));
            return;
        }
        // j从i向后枚举分割终点：取s[i ~ j]作为一段
        for(int j = i; j < n; j++) {
            if(flag[i][j]) { // 如果i到j是回文，才选这个分割
                ans.add(s.substring(i,j + 1)); // s.substring(i,j+1)：Java substring 左闭右开，所以要j+1才能取到下标j字符
                dfs(s, j + 1);
                ans.remove(ans.size() - 1); // 回溯删除最后添加的子串，尝试下一种切割方式
            }
        }
    }
}
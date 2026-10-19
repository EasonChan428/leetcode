import java.util.*;

class P0022GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        // 回溯法
        List<String> res = new ArrayList<String>();
        // 这里的cur用StringBuilder是时间最优
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }
    // 跟踪到目前为止放置的左括号和右括号的数目
    private void backtrack(List<String> res, StringBuilder cur, int open, int close, int n) { 
        if(cur.length() == n * 2) {
            res.add(cur.toString());
            return;
        }
        if(open < n) {
            cur.append('(');
            backtrack(res, cur, open + 1, close, n);
            cur.deleteCharAt(cur.length() - 1);
        }
        if(close < open) { // 注意这里的判断条件，如果右括号数量小于左括号的数量，我们才可以放一个右括号
            cur.append(')');
            backtrack(res, cur, open, close + 1, n);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}

/*
// 递归法
class Solution {
    List<String> res = new ArrayList<String>();
    public List<String> generateParenthesis(int n) {
        if(n <= 0) return res;
        dfs("", n, n);
        return res;
    }

    private void dfs(String str, int left, int right) {
        if(left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if(left == right) {
            // 剩余左右括号数相等，下一个只能用左括号
            dfs(str + "(", left - 1, right);
        } else if(left < right) {
            // 剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if(left > 0) {
                dfs(str + "(", left - 1, right);
            }
            dfs(str + ")", left, right - 1);
        }
    }
}
*/
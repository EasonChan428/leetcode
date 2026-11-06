import java.util.*;

class P0032LongestValidParentheses {
    public int longestValidParentheses(String s) {
        // 用栈模拟一遍，将所有无法匹配的括号的位置全部置0
        // 经过这样的处理后, 此题就变成了寻找最长的连续的1的长度
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int n = s.length();
        int[] flag = new int[n];
        Arrays.fill(flag, 1);

        // 第一遍遍历：标记多余的右括号，匹配成功则弹出栈
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(stack.isEmpty()) {
                    flag[i] = 0;
                } else {
                    stack.pop();
                }
            }
        }
        // 弹出未匹配的左括号，全部标记为无效
        while(!stack.isEmpty()) {
            flag[stack.pop()] = 0;
        }

        int res = 0;
        int len = 0;
        // 遍历标记数组，统计最长连续有效段的长度
        for(int i = 0; i < n; i++) {
            if(flag[i] == 0) {
                len = 0;
                continue;
            }
            len++;
            res = Math.max(res, len);
        }
        return res;
    }
}

// 【可选解法二】动态规划：只扫一遍、不用栈，O(n) 时间 O(n) 空间
// 关键是把状态定义成「以 s[i] 结尾」（钉住右端点），这样才能写出「在已有有效串后面接一对括号」的递推
// 下面注释块里的类名是 class Solution，取消注释即可单独提交（和外层 P0032LongestValidParentheses 不重名）
/*
class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        // dp[i] 表示「以 s[i] 结尾」的最长有效括号子串长度
        // 注意不是「前 i 个字符内」的最长长度 —— 必须是「必须以 s[i] 收尾」，否则接不上去
        // 不以 ')' 结尾的位置（也就是 '('）dp 恒为 0，所以数组默认值就够用
        int[] dp = new int[s.length()];
        // 从 1 开始：单个字符既不能成对，也没有前驱，dp[0] 必然是 0
        for (int i = 1; i < s.length(); i++) {
            // 只有 s[i] 是 ')' 才可能让以它结尾的串变有效；s[i] 是 '(' 时 dp[i] 保持 0
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 情况 A：s[i-1] 是 '('，形状是 "...( )"，这两个字符自己配成一对，贡献 +2
                    // 再往前「紧挨着」的有效串就是 dp[i-2]，直接拼上
                    // 例："()()" 在 i=3 时，dp[1]=2，于是 dp[3]=dp[1]+2=4
                    // i==1 时 i-2==-1 越界，说明前面没有串可拼，补 0 即可
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 情况 B：s[i-1] 也是 ')'，形状是 "...) )"
                    // 前一个 ')' 已经凑出一段长度为 dp[i-1] 的有效串，它整段占住 [i-dp[i-1], i-1]
                    // 所以能跟 s[i] 配对的那个 '(' 只可能在它的左边一位：下标 i - dp[i-1] - 1
                    // 条件 i - dp[i-1] > 0 就是在保证这个下标不越界（等价于 i-dp[i-1]-1 >= 0）
                    // 配对成功后是三段相加：
                    //   ① 中间那段已经有效的 dp[i-1]
                    //   ② 新配成的这一对，+2
                    //   ③ 这对括号左边若还紧挨着一段有效串，就是 dp[i-dp[i-1]-2]
                    // 例："()(())" 在 i=5 时：dp[4]=2，i-dp[4]-1 = 2，s[2]=='(' 配对成功
                    //   dp[5] = dp[4] + 2 + dp[5-2-2=1] = 2 + 2 + 2 = 6
                    dp[i] = dp[i - 1]
                          + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0)
                          + 2;
                }
                // 两个分支都不成立时 dp[i] 保持 0：这个 ')' 左边找不到能配对的 '('
                // 例："())" 的 i=2：s[1]==')'，i-dp[1] = 2-2 = 0 不满足 >0，配对失败 -> dp[2]=0
                // 例："))))" 的 i=1：s[0]==')'，i-dp[0]-1 = -1 越界 -> dp[1]=0（后面同理）
                // 另外要注意：dp[i] 只依赖更小的下标（i-1、i-2、i-dp[i-1]-1、i-dp[i-1]-2）
                // 所以一遍从前往后正序推就够了，不需要额外的判定顺序
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
*/
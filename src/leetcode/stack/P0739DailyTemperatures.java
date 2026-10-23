import java.util.*;

class P0739DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        // 单调栈 维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标
        int n = temperatures.length;
        int[] ans = new int[n]; // 初始化默认值为 0
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            int cur = temperatures[i];
            while(!stack.isEmpty() && cur > temperatures[stack.peek()]) { // 用while把堆积的尚未找到下一次更高温度的下标都排出
                int prv = stack.pop();
                ans[prv] = i - prv; // 记录等待天数到答案数组中
            }
            stack.push(i);
        }
        return ans;
    }
}
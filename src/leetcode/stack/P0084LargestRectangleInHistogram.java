import java.util.*;

class P0084LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        // 本质其实就是：在一维数组中对每一个数找到第一个比自己小的元素。这类“在一维数组中找第一个满足某种条件的数”的场景就是典型的单调栈应用场景。
        // 要求以当前高度为高的矩形面积，我们需要求出每一根柱子的左右最近的小于其高度的柱子，即左右边界
        // 单调栈中存放了 i 值。从栈底到栈顶，i 的值严格单调递增，同时对应的高度值也严格单调递增
        int ans = 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) { // 注意这里是<=,相等也要弹出后再继续找
                right[stack.pop()] = i; // 出栈时必定是右边第一次遇到比自己小的→确定右边界
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek()); // 进栈前栈顶是左边第一个比自己小的→确定左边界
            stack.push(i);
        }

        for(int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
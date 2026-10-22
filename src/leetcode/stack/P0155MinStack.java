import java.util.*;

class P0155MinStack {
    // 差值法（O (1) 额外空间，无辅助栈） 不难理解，注意背细节
    Deque<Long> stack; // 栈存「当前值与当前最小值的差值」，不存原始值（long 防溢出）
    long min;
    public P0155MinStack() {
        stack = new LinkedList<>();
    }
    
    public void push(int val) {
        if(stack.isEmpty()) {
            // 空栈：直接存0，最小值初始化为val
            stack.push(0L);
            min = val;
            return;
        }
        // 计算 val 与当前min的差值
        long diff = val - min;
        stack.push(diff);
        // 若新值更小，更新最小值
        if(val < min) min = val;
    }
    
    public void pop() {
        long diff = stack.pop();
        // diff<0 代表被弹出的元素就是当前最小值，需要回滚到上一个最小值
        if(diff < 0) {
            min = min - diff; // 旧min = 新min - diff
        }
    }
    
    public int top() {
        long diff = stack.peek();
        // diff>=0：原始值 = min + diff
        if(diff >= 0) return (int)(min + diff);
        // diff<0：栈顶就是最小值，直接返回min
        else return (int)min;
    }
    
    public int getMin() {
        return (int)min; // 注意要转成int
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
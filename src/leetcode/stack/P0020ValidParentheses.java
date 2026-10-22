import java.util.*;

class P0020ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()) {
            // 运用栈先进后出，后进先出的特性进行匹配
            if(c == '(') stack.push(')');
            else if(c == '[') stack.push(']');
            else if(c == '{') stack.push('}');
            // 当前右括号没有与相应左括号匹配就代表无效
            else if(stack.isEmpty() || c != stack.pop()) return false; // 注意！'||' 是从左到右执行，所以一定要先判断非空再pop，不然全错
        }
        return stack.isEmpty(); // 注意栈非空也代表无效，因为还有左括号没被匹配
    }
}
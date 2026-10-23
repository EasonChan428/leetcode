import java.util.*;

class P0394DecodeString {
    public String decodeString(String s) {
        Deque<Integer> stack_num = new LinkedList<>(); // 存储需要重复字符串的数字
        Deque<String> stack_str = new LinkedList<>(); // 存储待重复的字符串
        StringBuilder res = new StringBuilder();
        int num = 0;

        for(char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') {
                num = num * 10 + (c - '0'); // 把字符数字转换为数字
            } else if(c == '[') {
                stack_num.push(num); // 把括号前的数字压栈
                stack_str.push(res.toString()); //把括号前的字母(即将来等待被重复的字符串)压栈
                num = 0; //需重复数字归零（注意！因为 num 可能会是n位数，为了方便后续统一x10处理，必须归零
                res = new StringBuilder(); // res 也要重置为空
            } else if(c == ']') {
                int curNum = stack_num.pop(); //此次要重复的次数数字
                String lastStr = stack_str.pop(); //紧贴着这一组括号外的还没执行重复的字符串
                StringBuilder tmp = new StringBuilder();
                for(int i = 0; i < curNum; i++) {
                    tmp.append(res); // 本次重复的完整子串
                }
                res = new StringBuilder(lastStr + tmp); // 拼回外层还没执行重复的字符串作为新res
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }
}
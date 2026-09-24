import java.util.*;

class P0003LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s==null) return 0;
        HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        int max=0;
        int left=0;
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                left=Math.max(left,map.get(s.charAt(i))+1);
                /*
                - `map.get(s.charAt(i))`：拿到这个字符上一次出现的索引
                - `map.get(...) +1`：窗口左边界要跳到「重复字符上一次位置的下一位」，保证窗口里没有重复字符
                - `Math.max(left, ...)` 这一步非常关键：防止左指针回退！比如abba到第二个a时
                */
            }
            map.put(s.charAt(i),i);
            max=Math.max(max,i-left+1);
        }
        return max;
    }
}
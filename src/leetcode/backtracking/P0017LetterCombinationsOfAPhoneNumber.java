import java.util.*;

class P0017LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        // 回溯递归版
        List<String> res = new ArrayList<String>();
        if(digits.length() == 0) return res;
        StringBuffer cur = new StringBuffer();
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, phoneMap, digits, 0, cur);
        return res;
    }

    private void backtrack(List<String> res, Map<Character, String> phoneMap, String digits, int index, StringBuffer cur) {
        // StringBuffer：可变字符缓冲区，底层是char[]，可以直接在原有数组追加 / 修改，不会频繁创建新对象
        if(index == digits.length()) {
            res.add(cur.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersL = letters.length();
            for(int i = 0; i < lettersL; i++) {
                cur.append(letters.charAt(i));
                backtrack(res, phoneMap, digits, index + 1, cur);
                cur.deleteCharAt(index);
            }
        }
    }
}
/*
// 非递归版，更容易理解
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        int n = digits.length();
        if (n == 0){
            return result;
        }
        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a","b","c"));
        map.put('3', Arrays.asList("d","e","f"));
        map.put('4', Arrays.asList("g","h","i"));
        map.put('5', Arrays.asList("j","k","l"));
        map.put('6', Arrays.asList("m","n","o"));
        map.put('7', Arrays.asList("p","q","r","s"));
        map.put('8', Arrays.asList("t","u","v"));
        map.put('9', Arrays.asList("w","x","y","z"));
        result = map.get(digits.charAt(0));
        if (n == 1){
            return result;
        }
        int i = 1;
        while (i < n){
            List<String> temp = new ArrayList<>();
            for (String s1 : result) {
                for (String s2 : map.get(digits.charAt(i))) {
                    temp.add(s1+s2);
                }
            }
            result = temp;
            i++;
        }
        return result;
    }
}
*/
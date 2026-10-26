import java.util.*;

class P0763PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<Integer>();
        int[] charsNum = new int[26]; // 记录s中每个字母出现次数
        int[] flags = new int[26]; // 记录当前字母有没有被遍历到过
        int curL = 0; // 记录当前字母段长度
        int flag = 0; // 记录还需要加入的字母次数
        for(char c : s.toCharArray()) {
            charsNum[c - 'a']++;
        }
        for(char c : s.toCharArray()) {
            // 当前字母没有被遍历到过
            if(flags[c - 'a'] == 0) {
                flags[c - 'a']++;
                flag = flag + charsNum[c - 'a'];
            }
            flag--;
            curL++;
            // flag为零时出现符合要求的字母段，把长度加入res随后清零
            if(flag == 0) {
                res.add(curL);
                curL = 0;
            }
        }
        return res;
    }
}
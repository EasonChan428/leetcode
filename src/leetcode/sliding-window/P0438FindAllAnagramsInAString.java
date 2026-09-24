import java.util.*;

class P0438FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        int sLen=s.length(),pLen=p.length();
        if(sLen<pLen) return new ArrayList<Integer>();

        List<Integer>ans=new ArrayList<Integer>();
        int[] sCount=new int[26];
        int[] pCount=new int[26];
        // ========== 初始化窗口：第一个窗口 [0, pLen-1] ==========
        for(int i=0;i<pLen;i++){
            sCount[s.charAt(i)-'a']+=1;
            pCount[p.charAt(i)-'a']+=1;
        }
        // 判断第一个窗口是否是异位词，是的话起点0加入结果
        if(Arrays.equals(sCount,pCount)){
            ans.add(0);
        }
        // ========== 滑动窗口向右移动 ==========
        // 一共移动 sLen-pLen 次
        for(int i=0;i<sLen-pLen;i++){
            //移出最左字符
            sCount[s.charAt(i)-'a']-=1;
            //移入最右的下一位字符
            sCount[s.charAt(i+pLen)-'a']+=1;
            // 比较两个计数数组，相等说明当前窗口是异位词
            if(Arrays.equals(sCount,pCount)){
                ans.add(i+1);
            }
        }
        return ans;
    }
}
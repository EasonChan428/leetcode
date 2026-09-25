class P0076MinimumWindowSubstring {
    public String minWindow(String S, String t) {
        /*
        我们可以用滑动窗口的思想解决这个问题。在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 r 指针，和一个用于「收缩」窗口的 l 指针。在任意时刻，只有一个指针运动，而另一个保持静止。我们在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。当窗口包含 t 全部所需的字符后，如果能收缩，我们就收缩窗口直到得到最小窗口。
        */
        int[] cnt=new int [128];// 窗口内字符计数，ASCII 0~127，大小写字母全部覆盖
        for(char c:t.toCharArray()){
            cnt[c]++;
        }
        char[] s=S.toCharArray();
        int m=s.length;
        int need=t.length();
        int ansL=-1;
        int ansR=m;
        int left=0;
        for(int right=0;right<m;right++){
            char rChar=s[right];
            if(cnt[rChar]>0){
                need--;
            }
            cnt[rChar]--;  
            while(need==0){
                if(right-left<ansR-ansL){
                    ansL=left;
                    ansR=right;
                }
                char lChar=s[left];
                left++;
                cnt[lChar]++;
                if(cnt[lChar]>0) need++;
            }
        }
        return ansL==-1 ? "" : S.substring(ansL,ansR+1); //Java substring 左闭右开,所以右边界是ansR+1.
    }
}
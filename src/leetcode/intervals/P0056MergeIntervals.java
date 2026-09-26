import java.util.*;

class P0056MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int [][] ans = new int[n][2];
        int size = 0;

        if(n==0){
            return new int [0][2];
        }
        //用Lambda 表达式简写 Comparator 接口进行左排序
        Arrays.sort(intervals,(arr1,arr2)->arr1[0]-arr2[0]);
        
        ans[0][0]=intervals[0][0];
        ans[0][1]=intervals[0][1];
        for(int i=0;i<n;i++){
            int left=intervals[i][0];
            int right=intervals[i][1];
            if(ans[size][1]<left){
                size++;
                ans[size][0]=left;
                ans[size][1]=right;
            }else{
                ans[size][1]=Math.max(ans[size][1],right);
            }
        }
        return Arrays.copyOf(ans,size+1);//如果保留我自己的写法就一定要记得这里的核心修复：用只Array.copyOf()方法取size+1个有效区间返回
    }
}
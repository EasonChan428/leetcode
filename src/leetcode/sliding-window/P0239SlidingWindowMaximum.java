import java.util.*;

class P0239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //优先队列思路：大根堆（优先队列），堆里保存`[数值,下标]`，堆顶永远是当前最大元素；堆里允许存在已经滑出窗口的过期元素，只在取堆顶的时候把过期元素弹出。
        int n=nums.length;
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int []pair1,int []pair2){
                // 数值不等：大的放堆顶；数值相等，下标大的在前
                return pair1[0]!=pair2[0] ? pair2[0] - pair1[0] : pair2[1]-pair1[1];
            }
        });//Java 默认`PriorityQueue`是小根堆，重写`compare`改成大根堆
        for(int i=0;i<k;i++){
            pq.offer(new int[]{nums[i],i});
        }
        int [] ans=new int[n-k+1];
        ans[0]=pq.peek()[0];
        for(int i=k;i<n;i++){
            pq.offer(new int[]{nums[i],i});
            // 关键：堆顶下标 <= i-k 说明堆顶元素已经在窗口左边，过期了，删掉
            while (pq.peek()[1]<=i-k){
                pq.poll();
            }
            ans[i-k+1]=pq.peek()[0];
        }
        return ans;
    }
}
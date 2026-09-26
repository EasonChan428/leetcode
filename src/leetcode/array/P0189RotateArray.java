class P0189RotateArray {
    public void rotate(int[] nums, int k) {
        if(nums==null||k==0) return;
        int n=nums.length;
        if(k>n) k=k%n;//重要一步不能忘记
        // 反转整个数组
        int left=0;
        int right=n-1;
        while(left<right){
            int t=nums[left];
            nums[left]=nums[right];
            nums[right]=t;
            left++;
            right--;
        }
        // 反转前k个元素
        left=0;
        right=k-1;
        while(left<right){
            int t=nums[left];
            nums[left]=nums[right];
            nums[right]=t;
            left++;
            right--;
        }
        // 反转剩下元素
        left=k;
        right=n-1;
        while(left<right){
            int t=nums[left];
            nums[left]=nums[right];
            nums[right]=t;
            left++;
            right--;
        }    
    }
}


/*
个人未优化解法
class Solution {
    public void rotate(int[] nums, int k) {
        if(nums==null||k==0) return;
        int n=nums.length;
        int[] ans=new int[n];
        if(k>n) k=k%n;//容易遗漏的重要边界判断
        for(int i=0;i<k;i++){
            ans[i]=nums[n-k+i];
        }
        for(int i=k;i<n;i++){
            ans[i]=nums[i-k];
        }
        for(int i=0;i<n;i++){
            nums[i]=ans[i];
        }
    }
}
*/
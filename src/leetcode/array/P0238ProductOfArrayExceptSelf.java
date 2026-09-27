class P0238ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        //用两个数组存储前后缀积
        if(nums == null) return new int[0];
        int n = nums.length;
        int[] left = new int [n];
        int[] right = new int [n];
        int[] ans = new int [n];

        left[0] = 1;
        for(int i = 1; i < n; i++){
            left[i] = left[i-1] * nums[i-1];
        }

        right[n-1] = 1;
        for(int i = n-2; i >= 0; i--){
            right[i] = right[i+1] * nums[i+1];
        }

        for(int i = 0; i < n; i++){
            ans[i] = left[i] * right[i];
        }

        return ans;
    }
}
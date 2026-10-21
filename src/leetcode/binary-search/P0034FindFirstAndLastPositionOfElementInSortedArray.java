class P0034FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        // 两次二分查找，分开查找第一个和最后一个
        int l = 0;
        int r = nums.length - 1;
        int first = -1;
        int last = -1;

        // 找第一个等于target的位置
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) {
                //重点:先存储当前和target相等的mid到first，然后在mid的左半区继续寻找下标更小的
                first = mid;
                r = mid - 1;
            } else if(nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        // 找最后一个等于target的位置
        l = 0;
        r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) {
                //重点:先存储当前和target相等的mid到last，然后在mid的右半区继续寻找下标更大的
                last = mid;
                l = mid + 1;
            } else if(nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return new int[]{first,last};
    }
}
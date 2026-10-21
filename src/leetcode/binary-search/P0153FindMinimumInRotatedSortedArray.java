class P0153FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        // 左、中、右三个位置的值相比较，有以下几种情况：
        // 左值 < 中值, 中值 < 右值 ：没有旋转，最小值在最左边，可以收缩右边界
        // 左值 > 中值, 中值 < 右值 ：有旋转，最小值在左半边，可以收缩右边界
        // 左值 < 中值, 中值 > 右值 ：有旋转，最小值在右半边，可以收缩左边界
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        int l = 0;
        int r = n - 1;

        // 如果中值 < 右值，则最小值在左半边，可以收缩右边界;如果中值 > 右值，则最小值在右半边，可以收缩左边界。
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] < nums[r]) {
                r = mid; // 注意！因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处
            } else if(nums[mid] > nums[r]) {
                l = mid + 1;
            }
        }
        return nums[l]; // 循环结束，left == right，最小值输出nums[left]
    }
}
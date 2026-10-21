class P0033SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        // 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环
        // 只有在顺序区间内才可以通过区间两端的数值判断target是否在其中
        // 判断顺序区间还是乱序区间，只需要对比 left 和 right 是否是顺序对即可，left <= right，顺序区间，否则乱序区间
        // 每次二分都会至少存在一个顺序区间
        int n = nums.length;
        if(n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = n - 1;

        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) return mid;
            // 通过不断的用Mid二分，将整个数组划分成顺序区间和乱序区间，然后判断target是否在顺序区间，如果在顺序区间，下次循环就直接取顺序区间，如果不在，那么下次循环就取乱序区间
            if(nums[l] <= nums[mid]) {
                // left 到 mid 是顺序区间 
                if(nums[l] <= target && nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { 
                // mid 到 right 是顺序区间
                if(nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
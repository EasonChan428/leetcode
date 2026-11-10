class P0075SortColors {
    public void sortColors(int[] nums) {
        // 三个指针分别标记三个颜色区域的右边界（下一个该颜色应该放置的下标）
        int c0 = 0, c1 = 0, c2 = 0;

        for(int num : nums) {
            // 遍历核心原则：从右往左依次覆盖（因为num2 ≥ num1 ≥ num0，先改最右侧的2区，再改中间1区，最后改最左侧0区，避免覆盖还未使用的位置）
            if(num == 0) {
                nums[c2++] = 2;
                nums[c1++] = 1;
                nums[c0++] = 0;
            } else if(num == 1) {
                nums[c2++] = 2;
                nums[c1++] = 1;
            } else {
                nums[c2++] = 2;
            }
        }
    }
}
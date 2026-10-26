class P0045JumpGameII {
    public int jump(int[] nums) {
        // 记录当前能跳跃到的位置的边界下标
        int border = 0;
        // 记录在边界范围内，能跳跃的最远位置的下标
        int maxPosition = 0;
        // 记录所用步数
        int step = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            // 继续往下遍历，统计边界范围内，哪一格能跳得更远，每走一步就更新一次能跳跃的最远位置下标
            // 其实就是在统计下一步的最优情况
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(border == i) {
                border = maxPosition;
                step++;
            }
            // 剪枝
            if(border >= nums.length - 1) break;
        }
        return step;
    }
}
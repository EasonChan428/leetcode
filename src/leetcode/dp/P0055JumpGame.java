class P0055JumpGame {
    public boolean canJump(int[] nums) {
        // 把可跳的步数看成拥有的能量，每跳一步消耗一格能量
        int myPower = nums[0];
        for(int i = 1; i < nums.length; i++) {
            myPower--;
            // 能量没续上，失败
            if(myPower < 0) {
                return false;
            }
            // 当前格子拥有更多能量，替换
            if(nums[i] > myPower) {
                myPower = nums[i];
            }
        }
        // 到终点了，看看能量是否大于等于0
        return myPower >= 0;
    }
}

/*
// 倒序遍历，只要某一个位置满足到达终点，就只需考虑前面的是否能够到达该位置即可
class Solution {
    public static boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = lastPos; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
*/
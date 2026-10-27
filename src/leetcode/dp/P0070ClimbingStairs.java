class P0070ClimbingStairs {
    public int climbStairs(int n) {
        // f(x)=f(x−1)+f(x−2)
        // 用「滚动数组思想」把空间复杂度优化成 O(1)
        int num1 = 0, num2 = 0, res = 1;
        for(int i = 1; i <= n; i++) {
            num1 = num2;
            num2 = res;
            res = num1 + num2;
        }
        return res;
    }
}
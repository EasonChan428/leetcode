class P0279PerfectSquares {
    public int numSquares(int n) {
        // 动态规划
        int[] f = new int[n + 1]; // f[i] 表示最少需要多少个数的平方来表示整数 i
        for(int i = 1; i <= n; i++) {
            // 从1求到n
            int minN = Integer.MAX_VALUE;
            for(int j = 1; j * j <=i; j++) { // 枚举「最后一个使用的完全平方数」为 j²
                minN = Math.min(minN, f[i - j * j]); // 核心状态表达式
            }
            f[i] = minN + 1; // 凑出 i 的最少个数 = 凑出 i-j² 的最少个数 + 1
        }
        return f[n]; // 返回凑出 n 的最少个数
    }
}
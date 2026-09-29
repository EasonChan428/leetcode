class P0048RotateImage {
    public void rotate(int[][] matrix) {
        //思路：两次翻转等于一次旋转
        int n = matrix.length;
        // 水平翻转
        for(int i = 0; i < n / 2; i++){
            for(int j = 0; j < n; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - i -1][j];
                matrix[n - i -1][j] = t;
            }
        }
        // 主对角线翻转
        for(int i = 1;i < n; i++){
            for(int j = 0; j < i; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
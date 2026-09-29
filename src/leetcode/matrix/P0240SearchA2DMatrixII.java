class P0240SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 类似于二叉搜索树，从矩阵 matrix 左下角元素（索引设为 (i, j) ）开始遍历，并与目标值对比：
        // 当 matrix[i][j] > target 时，执行 i-- ，即消去第 i 行元素。
        // 当 matrix[i][j] < target 时，执行 j++ ，即消去第 j 列元素。
        // 当 matrix[i][j] = target 时，返回 true ，代表找到目标值。
        // 若行索引或列索引越界，则代表矩阵中无目标值，返回 false 。
        // 每轮 i 或 j 移动后，相当于生成了“消去一行（列）的新矩阵”， 索引(i,j) 指向新矩阵的左下角元素（标志数），因此可重复使用以上性质消去行（列）。
        
        int i = matrix.length - 1;
        int j = 0;

        while(i >= 0 && j < matrix[0].length){
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] > target){ 
                i--;
            }else if(matrix[i][j] < target){
                j++;
            }//注意：else不能漏！ 
        }

        return false;
    }
}
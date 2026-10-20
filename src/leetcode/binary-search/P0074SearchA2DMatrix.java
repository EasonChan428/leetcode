class P0074SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 两次二分查找，注意边界判断即可
        int u = 0;
        int d = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        while(u <= d) {
            int rowmid = u + (d - u) / 2;
            if(matrix[rowmid][0] < target) {
                u = rowmid + 1;
            } else {
                d = rowmid - 1;
            }
        }
        if(u < matrix.length){
            if(matrix[u][0] == target) return true;
            if(u <= 0) return false;
        }
        int row = u - 1;
        while(l <= r) {
            int colmid = l + (r - l) / 2;
            if(matrix[row][colmid] < target) {
                l = colmid + 1;
            } else {
                r = colmid - 1;
            }    
        }
        if(l < matrix[0].length) {
            if(matrix[row][l] == target) return true;
        }
        return false;
    }
}
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

//一次二分的写法（把二维下标换算成一维，不用处理行边界）—— 面试可能问
/*
class Solution {
    // 思路：题目保证「下一行的第一个数 > 上一行的最后一个数」，
    //      所以整块矩阵其实等价于一个长度为 m*n 的升序一维数组。
    //      于是可以直接在 [0, m*n-1] 这个一维区间上做一次标准二分，
    //      取值时把一维下标 mid 换算成二维坐标：行 = mid / n，列 = mid % n。
    //
    // 好处：完全不需要像两次二分那样判断行边界，也就没有 u == 0 / u == m 这类特判，
    //      代码更短，出错概率更低。
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0;
        int r = m * n - 1;                       // 把所有元素看成一维数组，右端是 m*n-1
        while (l <= r) {
            int mid = l + (r - l) / 2;           // 防溢出写法
            int val = matrix[mid / n][mid % n];  // 一维下标 → 二维坐标
            if (val == target) {
                return true;
            } else if (val < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
*/
import java.util.*;

class P0054SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int u = 0;
        int d = m - 1;
        int l = 0;
        int r = n - 1;

        while(true){
            for(int i = l; i <= r; i++) ans.add(matrix[u][i]);
            u++;
            if(u>d) break;

            for(int i = u; i <= d; i++) ans.add(matrix[i][r]);
            r--;
            if(r<l) break;

            for(int i = r; i >= l; i--) ans.add(matrix[d][i]);
            d--;
            if(d<u) break;

            for(int i = d; i >= u; i--) ans.add(matrix[i][l]);
            l++;
            if(l>r) break;
        }
        return ans;
    }
}
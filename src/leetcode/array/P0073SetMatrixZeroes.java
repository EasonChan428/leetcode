class P0073SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int flagM = 0;
        int flagN = 0;
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                flagM = 1;
                break;
            }
        }
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0){
                flagN = 1;
                break;
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                   matrix[i][j] = 0;
                }
            }
        }

        if(flagM == 1){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
        if(flagN == 1){
            for(int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }
    }
}
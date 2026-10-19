class P0079WordSearch {
    public boolean exist(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        boolean[][] isVisited = new boolean[h][w];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                // 对每一个位置 (i,j) 都调用函数 check(i,j,0) 进行检查：只要有一处返回 true，就说明网格中能够找到相应的单词
                boolean flag = check(board, isVisited, word, i, j, 0);
                if(flag) return true;
            }
        }
        // 否则说明不能找到
        return false;
    }

    private boolean check(char[][] board, boolean[][] isVisited, String word, int i, int j, int cur) {
        if(board[i][j] != word.charAt(cur)) {
            // 如果 board[i][j] != word[cur]，当前字符不匹配，直接返回 false
            return false;
        } else if(cur == word.length() - 1) {
            // 如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回 true
            return true;
        }
        // 为了防止重复遍历相同的位置，需要额外维护一个与 board 等大的 isVisited 数组，用于标识每个位置是否被访问过。
        isVisited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean res = false;
        for(int[] dir : directions) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if(newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if(!isVisited[newi][newj]) {
                    // 每次遍历相邻位置时，需要跳过已经被访问的位置
                    boolean flag = check(board, isVisited, word, newi, newj, cur + 1);
                    if(flag) {
                        res = true;
                        break;
                    }
                }
            }
        }
        isVisited[i][j] = false; // 将isVisited回溯
        return res;
    }
}
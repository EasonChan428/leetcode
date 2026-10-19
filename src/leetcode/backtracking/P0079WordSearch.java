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

//剪枝优化（先反转 word 再搜）—— 面试可能问
/*
class Solution {
    // 原理：路径是可以反向走的 —— 网格里能找到 word，就等价于能找到它的反转串
    //      （同一串格子倒着读，就是反转串）。所以只要让 DFS 从"网格里字母更稀有的一端"起步，
    //      首字符匹配的起点就会变少，无效分支自然大幅减少。
    //
    // 为什么不是无脑反转：万一首字符本来才是稀有的那个，反转反而会变慢。
    //      所以先统计两端的出现次数，只有「末尾字符出现得更少」时才反转。
    //
    // 注意：这只改变搜索顺序，不改变答案，正确性完全不受影响。
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;

        // 统计 word 首、尾字符在网格里各出现多少次
        char firstCh = word.charAt(0);
        char lastCh = word.charAt(word.length() - 1);
        int cntFirst = 0, cntLast = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == firstCh) cntFirst++;
                if (board[i][j] == lastCh) cntLast++;
            }
        }
        // 末尾字符更稀有 → 反转 word，让 DFS 从少的那一端起步
        if (cntLast < cntFirst) {
            word = new StringBuilder(word).reverse().toString();
        }

        // 下面是原来的 DFS，逻辑与主解完全一致
        boolean[][] isVisited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (check(board, isVisited, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean check(char[][] board, boolean[][] isVisited, String word, int i, int j, int cur) {
        if (board[i][j] != word.charAt(cur)) return false;
        if (cur == word.length() - 1) return true;      // 最后一个字符也匹配 → 找到了
        isVisited[i][j] = true;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean found = false;
        for (int[] d : dirs) {
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length && !isVisited[ni][nj]) {
                if (check(board, isVisited, word, ni, nj, cur + 1)) {
                    found = true;
                    break;
                }
            }
        }
        isVisited[i][j] = false;                        // 回溯：撤销访问标记
        return found;
    }
}

// 另一种常见剪枝（可以和上面的叠用）：如果 word 里某个字符出现的次数
// 多于它在 board 里出现的次数，那一定拼不出来 → 直接 return false，连 DFS 都不用进。
*/
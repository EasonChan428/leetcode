import java.util.*;

class P0994RottingOranges {
    public int orangesRotting(int[][] grid) {
        // 这个问题实际上就是求腐烂橘子到所有新鲜橘子的最短路径，所以使用BFS层序遍历
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0; // count 表示新鲜橘子的数量
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    count++;
                }else if(grid[r][c] == 2) {
                    // 存储一开始腐烂的橘子
                    queue.add(new int[]{r, c});
                }
            }
        }

        int round = 0; // round 表示腐烂的轮数，或者分钟数
        while(count > 0 && !queue.isEmpty()) {
            round ++;
            int level = queue.size();
            for(int i = 0; i < level; i++) {
                // 先进先出,处理当前腐烂的橘子
                int[] orange = queue.poll();
                int r = orange[0];
                int c = orange[1];
                // 腐烂上一个
                if(r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    count--;
                    queue.add(new int[]{r - 1, c});
                }
                // 腐烂下一个
                if(r + 1 < m && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    count--;
                    queue.add(new int[]{r + 1, c});
                }
                // 腐烂左一个
                if(c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    count--;
                    queue.add(new int[]{r, c - 1});
                }
                // 腐烂右一个
                if(c + 1 < n && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    count--;
                    queue.add(new int[]{r, c + 1});
                }
            }
        }
        if(count > 0) {
            return -1;
        }else {
            return round;
        }
    }
}
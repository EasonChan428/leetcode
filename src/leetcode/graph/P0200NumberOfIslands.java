/**
 * 网格 DFS 遍历的框架代码：
 *
 * void dfs(int[][] grid, int r, int c) {
 *     // 判断 base case：如果坐标 (r, c) 超出了网格范围，直接返回
 *     if (!inArea(grid, r, c)) {
 *         return;
 *     }
 *     // 访问上、下、左、右四个相邻结点
 *     dfs(grid, r - 1, c);
 *     dfs(grid, r + 1, c);
 *     dfs(grid, r, c - 1);
 *     dfs(grid, r, c + 1);
 * }
 *
 * // 判断坐标 (r, c) 是否在网格中
 * boolean inArea(int[][] grid, int r, int c) {
 *     return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
 * }
 *
 * // 如何避免重复遍历？答案是标记已经遍历过的格子。以岛屿问题为例，需要在所有值为 1 的陆地格子上
 * // 做 DFS 遍历。每走过一个陆地格子，就把格子的值改为 2，这样当遇到 2 的时候，就知道这是遍历过的格子了。
 */
class P0200NumberOfIslands {
    public int numIslands(char[][] grid) {
        int ans = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0;j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    // 岛屿个数只需要dfs外计数即可，面积需要dfs内部也进行计数
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int r, int c) {
        // 如果超出网格范围或者遍历到海洋则直接返回
        if(!inArea(grid, r, c)) return;
        // 遍历过的陆地直接返回
        if(grid[r][c] == '2') return;
        grid[r][c] = '2';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] != '0';
    }
}
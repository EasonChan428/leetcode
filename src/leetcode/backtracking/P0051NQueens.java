import java.util.*;

class P0051NQueens {
    // 回溯的具体做法：使用一个数组记录每行放置的皇后的列下标，依次在每一行放置一个皇后。每次新放置的皇后都不能和已经放置的皇后之间有攻击：即新放置的皇后不能和任何一个已经放置的皇后在同一列以及同一条斜线上，并更新数组中的当前行的皇后列下标。当 N 个皇后都放置完毕，则找到一个可能的解。当找到一个可能的解之后，将数组转换成表示棋盘状态的列表，并将该棋盘状态的列表加入返回列表

    // N皇后问题斜线判断时有两个方向，一个方向每个位置满足行下标与列下标之差相等，另一个方向每个位置满足行下标与列下标之和相等
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1); // 初始所有行未放置皇后，标记为-1(数组默认初始化都是0，避免和第0列区分不开)
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> dir1 = new HashSet<Integer>();
        Set<Integer> dir2 = new HashSet<Integer>();
        backtrack(res, queens, n, 0, columns, dir1, dir2); // 从第0行开始回溯放置皇后
        return res;
    }

    private void backtrack(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> dir1, Set<Integer> dir2) {
        // row == n代表 0~n-1 行全部成功放置皇后，得到一组合法解，把内部数组转成棋盘字符串存入结果
        if(row == n) {
            List<String> board = getBoard(queens, n);
            res.add(board);
        } else {
            for(int i = 0; i < n; i++) { // i是当前尝试的列号
                if(columns.contains(i)) {
                    // 冲突1：这一列已经有皇后，跳过
                    continue;
                }
                int t1 = row - i;
                if(dir1.contains(t1)) {
                    // 冲突2：主对角线（左上→右下），用 row - i 唯一标识
                    continue;
                }
                int t2 = row + i;
                if(dir2.contains(t2)) {
                    // 冲突3：副对角线（右上→左下），用 row + i 唯一标识
                    continue;
                }
                // 无冲突，正式放置皇后
                queens[row] = i;
                columns.add(i);
                dir1.add(t1);
                dir2.add(t2);
                backtrack(res, queens, n, row + 1, columns, dir1, dir2);
                // 回溯：撤销当前选择，尝试当前行的下一个列
                queens[row] = -1;
                columns.remove(i);
                dir1.remove(t1);
                dir2.remove(t2);
            }
        }
    }

    private List<String> getBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for(int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.'); // 整行先填满'.'
            row[queens[i]] = 'Q'; // 对应皇后位置放'Q'
            board.add(new String(row));
        }
        return board;
    }
}
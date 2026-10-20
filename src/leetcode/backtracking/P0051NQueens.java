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

//进阶写法：用三个 int 位掩码代替三个 HashSet（更快、常量更小）—— 面试加分
/*
class Solution {
    // 思路与主解完全一样，只是把「哪些列 / 对角线被占用」从 HashSet 换成了 int 的二进制位：
    //      int 的第 i 位 = 1，表示第 i 列（或第 i 条对角线）已被占用。
    //      判断冲突只要一次位运算，没有哈希开销。
    //
    // 三个掩码的含义与「每层怎么移位」：
    //   col  第 i 位 = 第 i 列被占
    //   d1   "\" 方向（左上→右下）：本行占第 c 列，下一行同一斜线落在第 c+1 列 → 每层左移 1 位
    //   d2   "/" 方向（右上→左下）：本行占第 c 列，下一行同一斜线落在第 c-1 列 → 每层右移 1 位
    //
    // 为什么不需要撤销：三个掩码是「算出一个新值往下传」的（位运算生成新 int），
    //     而不是共享的集合，所以天然不存在「回溯后忘了 remove」的问题 —— 这也是它比 Set 版简洁的地方。
    private int n;
    private int[] queens;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.queens = new int[n];
        Arrays.fill(this.queens, -1);
        this.res = new ArrayList<>();          // 每次调用都新建，避免上一个用例的结果残留
        dfs(0, 0, 0, 0);
        return res;
    }

    // row：当前行；col / d1 / d2：本行被占用的列与两条对角线（都已对齐到本行的列号）
    private void dfs(int row, int col, int d1, int d2) {
        if (row == n) {
            res.add(buildBoard());
            return;
        }
        // 本行可选的列：n 位全 1 的掩码，去掉已被「列 / 两条对角线」占用的位
        int available = ((1 << n) - 1) & ~(col | d1 | d2);
        while (available != 0) {
            int bit = available & (-available);           // 取出最低位的 1 —— 一个候选列
            available ^= bit;                             // 用掉这个候选，下一轮试下一个
            int c = Integer.numberOfTrailingZeros(bit);    // 该位对应第几列
            queens[row] = c;
            dfs(row + 1, col | bit, (d1 | bit) << 1, (d2 | bit) >> 1);
            queens[row] = -1;                             // 回溯：列/对角线不用撤销，因为是传值下去的
        }
    }

    private List<String> buildBoard() {
        List<String> board = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            char[] line = new char[n];
            Arrays.fill(line, '.');
            line[queens[r]] = 'Q';
            board.add(new String(line));
        }
        return board;
    }
}
*/
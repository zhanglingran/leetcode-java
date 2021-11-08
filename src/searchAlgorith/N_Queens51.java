package searchAlgorith;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个大小为 n 的正方形国际象棋棋盘，求有多少种方式可以放置 n 个皇后并使得她们互不攻击，即每一行、列、左斜、右斜最多只有一个皇后。
 * @DateTime: 2021/11/7 19:18
 */
public class N_Queens51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        if (n == 0) {
            return res;
        }


        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        boolean[] column = new boolean[n];
        boolean[] ldiag = new boolean[2 * n - 1];
        boolean[] rdiag = new boolean[2 * n];

        dfs(res, board, column, ldiag, rdiag, 0, n);

        return res;
    }

    private List<String> charToString(char[][] board) {
        List<String> res = new ArrayList<>();
        int len = board.length;

        for (int i = 0; i < len; i++) {
            res.add(new String(board[i]));
        }
        return res;
    }

    /**
     * 难点在于如何标记：
     *      因为每次在一行中选则一个位置放置皇后，之后再从下一行中放置皇后，那么无需对行进行标记。
     *      对列的标记：因为每次将Q放到某个列之后，整列不可再放置其他的Q，因此可以使用一维数组column来标记列
     *      对\对角线的标记：在同一个斜对角线上的元素，其 row - col 都相同，因此可以使用 rightDiag[] 数组来标记
     *          因为 row - col 可能会出现负数的情况，那么再其基础上再加上一个 n 即可解决问题
     *      对/对角线的标记：在同一个斜对角线上的元素，其 row + col 都相同，因此可以使用 leftDiag[] 数组来标记
     * @param res
     * @param board
     * @param column
     * @param ldiag
     * @param rdiag
     * @param row
     * @param n
     * @TODO 将N皇后问题总结成blog
     */
    private void dfs(List<List<String>> res, char[][] board,
                     boolean[] column, boolean[] ldiag,
                     boolean[] rdiag, int row, int n) {

        if (row == n) {
            res.add(charToString(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (column[i] || ldiag[row + i] || rdiag[row - i + n]) {
                continue;
            }
            board[row][i] = 'Q';
            column[i] = ldiag[row + i] = rdiag[row - i + n] = true;

            dfs(res, board, column, ldiag, rdiag, row+1, n);

            board[row][i] = '.';
            column[i] = ldiag[row + i] = rdiag[row - i + n] = false;
        }
    }


    public static void main(String[] args) {
        int n = 4;
        N_Queens51 obj = new N_Queens51();
        List<List<String>> res =  obj.solveNQueens(n);

        for (List<String> list : res) {
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println();
        }

    }
}

package searchAlgorith;

/**
 * @Author: ZhangLingRan
 * @Description: 将被X包围着的O全部替换为X， 要注意，一定是被X包围着的O
 * @DateTime: 2021/11/12 20:58
 */
public class SurroundedRegions130 {

    /**
     * 先标记，后处理
     * 所谓先标记：就是以board四周的'O'为起始点进行深搜，并将其全部标记
     * 所谓后处理：就是对board里边未被标记过的的O点进行深搜处理，并将O换为X
     * @param board
     */
    public void solve(char[][] board) {

        int row = board.length;
        int col = board[0].length;

        boolean[][] flag = new boolean[row][col];

        if (row <= 2 || col <= 2) {
            return;
        }

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs1(i, 0, board, flag, row, col);
            }
            if (board[i][col-1] == 'O') {
                dfs1(i, col-1, board, flag, row, col);
            }
        }

        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                dfs1(0, i, board, flag, row, col);
            }
            if (board[row-1][i] == 'O') {
                dfs1(row-1, i, board, flag, row, col);
            }
        }

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (board[i][j] == 'O' && !flag[i][j]) {
                    dfs(i, j, board, row, col, flag);
                }
            }
        }


    }

    private void dfs1(int x, int y, char[][] board, boolean[][] flag, int row, int col) {
        flag[x][y] = true;
        int[][] direct = {{0, 0, -1, 1}, {-1, 1, 0, 0}};

        for (int k = 0; k < 4; k++) {
            int xx = x + direct[0][k];
            int yy = y + direct[1][k];

            if (xx > 0 && xx < row - 1 && yy > 0 && yy < col - 1 && board[xx][yy] == 'O' && !flag[xx][yy]) {
                flag[xx][yy] = true;
                dfs1(xx, yy, board, flag, row, col);
            }

        }
    }

    private void dfs(int x, int y, char[][] board, int row, int col, boolean[][] flag) {

        board[x][y] = 'X';

        int[][] direct = {{0, 0, -1, 1}, {-1, 1, 0, 0}};

        for (int k = 0; k < 4; k++) {
            int xx = x + direct[0][k];
            int yy = y + direct[1][k];

            if (xx > 0 && xx < row - 1 && yy > 0 && yy < col - 1 && board[xx][yy] == 'O' && !flag[xx][yy]) {
                board[xx][yy] = 'X';
                dfs(xx, yy, board, row, col, flag);
            }

        }
    }
}

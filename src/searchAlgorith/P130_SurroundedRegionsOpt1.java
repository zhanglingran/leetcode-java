package searchAlgorith;

/**
 * @Author: ZhangLingRan
 * @Description: 将被X包围着的O全部替换为X， 要注意，一定是被X包围着的O
 * @DateTime: 2021/11/12 20:58
 */
public class P130_SurroundedRegionsOpt1 {

    /**
     * 先标记，后处理
     * 所谓先标记：就是以board四周的'O'为起始点进行深搜，并将其全部标记【可以开一个Boolean数组标记，也可在Board上直接标记】
     * 所谓后处理：只要未被标记的O全部变成X即可; 在之前的基础上省去了对未标记的点再进行DFS操作
     * @param board
     */
    public void solve(char[][] board) {

        int row = board.length;
        int col = board[0].length;

        if (row <= 2 || col <= 2) {
            return;
        }

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, board, row, col);
            }
            if (board[i][col-1] == 'O') {
                dfs(i, col-1, board, row, col);
            }
        }

        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                dfs(0, i, board, row, col);
            }
            if (board[row-1][i] == 'O') {
                dfs(row-1, i, board, row, col);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }else if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }


    }

    private void dfs(int x, int y, char[][] board, int row, int col) {

        board[x][y] = 'B';

        int[][] direct = {{0, 0, -1, 1}, {-1, 1, 0, 0}};

        for (int k = 0; k < 4; k++) {
            int xx = x + direct[0][k];
            int yy = y + direct[1][k];

            if (xx >= 0 && xx < row && yy >= 0 && yy < col && board[xx][yy] == 'O') {
                board[xx][yy] = 'B';
                dfs(xx, yy, board, row, col);
            }

        }
    }
}

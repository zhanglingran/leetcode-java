package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * @DateTime: 2021/11/22 10:12
 */
public class p63_MinimumPathSum {

    /**
     * dp[i][j] 表示 走到第i,j位置的时候，最小的路径值；因为只能从上边和左边过来，那么就得到了最终的状态转移方程；
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[row-1][col-1];
    }

}

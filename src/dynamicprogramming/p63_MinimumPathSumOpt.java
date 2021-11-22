package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * @DateTime: 2021/11/22 10:12
 */
public class p63_MinimumPathSumOpt {

    /**
     * dp[i][j] 表示 走到第i,j位置的时候，最小的路径值；因为只能从上边和左边过来，那么就得到了最终的状态转移方程；
     * 因为状态转移方程为 :  dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
     *
     * 状态压缩：
     *      因为 dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
     *      现将 dp压缩为一维数组，那么 dp[j] 表示当前层的状态，那么上一层的状态就是dp[j]本身的值，而当前层的状态用dp[j-1]来记录
     *      dp[j] = Math.min{dp[j], dp[j-1]} + grid[i][j]; 其中i表示当前循环到第i层；
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        int[] dp = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    dp[j] = (j == 0 ? 0 : dp[j-1]) + grid[i][j];
                } else {
                    if (j == 0) {
                        dp[j] += grid[i][j];
                    }else {
                        dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
                    }
                }

            }
        }

        return dp[col-1];
    }

}

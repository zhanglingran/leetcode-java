package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2021/11/23 21:28
 */
public class P1277_CountSquareSubmatricesWithAllOnes {

    public int countSquares(int[][] matrix) {
        int res = 0;

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row+1][col+1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i-1][j-1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                }
                res += dp[i][j];
            }
        }
        return res;
    }
}

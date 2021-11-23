package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * @DateTime: 2021/11/23 20:31
 *
 * 代码优化版本~
 */
public class P221_MaximalSquareOpt {


    /**
     * dp[i][j] 表示 以i,j为右下角的图形可构成的最大正方形的边长；
     * 如果matrix[i][j]=0,那么dp[i][j]=0
     * 如果matrix[i][j]=1,那么dp[i][j]就得判断其上、左、左前方的值是不是相同，
     *  如果相同那么在此基础上+1即是以i,j为右下角的正方形的边长
     *  如果不相同，那么就在这三个值之间选一个最小的，并在最小值的基础上+1得到新的边长
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int res = 0;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
                res = Math.max(dp[i][j], res);

            }
        }

        return res * res;
    }

    public static void main(String[] args) {
        P221_MaximalSquareOpt obj = new P221_MaximalSquareOpt();
        char[][] nums = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        obj.maximalSquare(nums);
    }
}

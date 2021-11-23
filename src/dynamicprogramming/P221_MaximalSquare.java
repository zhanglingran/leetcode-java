package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * @DateTime: 2021/11/23 20:31
 */
public class P221_MaximalSquare {


    /**
     * dp[i][j] 表示 以i,j为右下角的图形可构成的最大正方形的边长；
     * 如果matrix[i][j]=0,那么dp[i][j]=0
     * 如果matrix[i][j]=1,那么dp[i][j]就得判断其上、左、左前方的值是不是相同，如果相同那么在此基础上+1即是以i,j为右下角的正方形的边长
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if (i > 0 && j > 0) {
                        if (dp[i-1][j] == dp[i][j-1] && dp[i][j-1] == dp[i-1][j-1]) {
                            dp[i][j] = dp[i][j-1] + 1;
                        } else {
                            dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                        }
                    } else {
                        dp[i][j] = 1;
                    }
                }
                res = Math.max(dp[i][j], res);

            }
        }

        return res * res;
    }

    public static void main(String[] args) {
        P221_MaximalSquare obj = new P221_MaximalSquare();
        char[][] nums = {{'1','0','1','0','1'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        obj.maximalSquare(nums);
    }
}

package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * @DateTime: 2021/11/23 21:40
 *
 * 感觉是一个固定价值的背包问题
 */
public class P279_PerfectSquares {

    /**
     * 首先打表：来存储完全平方数，存放到 squares 中;
     * 获得n之后，对n开方，得到一个下标 n'：squares[n']就是距离n最大的完全平方数
     * 状态转移方程：dp[i] = min{dp[i-j*j]} + 1; 其中j<=sqrt(i)
     * @param n
     * @return
     */
    public int numSquares(int n) {

        int[] dp = new int[n+1];

        for (int i = 1; i <= n;i++) {
            int k = (int) Math.sqrt(Double.valueOf(i));
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                min = Math.min(min, dp[i-j*j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        P279_PerfectSquares obj = new P279_PerfectSquares();
        System.out.println(obj.numSquares(12));
    }

}

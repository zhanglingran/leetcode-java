package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * @DateTime: 2021/12/3 19:32
 */
public class P322_CoinChange {

    /**
     * dp[i] 总金额为i的时候，需要最少的金币数
     * dp[i] = min{ dp[i-coins[j]] }(其中 j=0->coins.length-1);
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            int tmp = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    tmp = Math.min(dp[i-coins[j]], tmp);
                }
            }
            if (tmp == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = tmp + 1;
            }
        }

        return dp[amount];
    }

}

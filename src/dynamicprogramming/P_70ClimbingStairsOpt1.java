package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @DateTime: 2021/11/18 22:39
 */
public class P_70ClimbingStairsOpt1 {


    /**
     * 分析：假设有n个台阶，那么要迈上第n个台阶，可以从第n-1个台阶向上迈一步；也可以从第n-2个台阶向上迈1步
     * 假设达到第n个台阶有f(n)种方案，那么到达第n-1个台阶就有f(n-1)种方案，到达n-2个台阶就有f(n-2)种方案
     * 综上： f(n) = f(n-1) + f(n-2)
     *
     * 将之前的递归方案修改，记录每次计算的状态，使用DP; dp[n]即等价于f(n)
     * @param n
     * @return
     */
    public int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


    /**
     * 在上述方法的基础上进行空间优化，用为第n个状态只于前两个状态相关，那么没必要开这么大的数组；用dp[i-1]表示到达第i个台阶的方案数
     * @param n
     * @return
     */
    public int climbStairsOpt(int n) {

        if (n <= 2) {
            return n;
        }

        int[] dp = new int[3];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i % 3] = dp[(i-1) % 3] + dp[(i-2) % 3];
        }

        return dp[(n-1) % 3];
    }

}

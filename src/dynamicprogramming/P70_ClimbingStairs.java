package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @DateTime: 2021/11/18 22:39
 */
public class P70_ClimbingStairs {


    /**
     * 分析：假设有n个台阶，那么要迈上第n个台阶，可以从第n-1个台阶向上迈一步；也可以从第n-2个台阶向上迈1步
     * 假设达到第n个台阶有f(n)种方案，那么到达第n-1个台阶就有f(n-1)种方案，到达n-2个台阶就有f(n-2)种方案
     * 综上： f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 当n=1的时候，只有1种方案
        if (n == 1) {
            return 1;
        }

        // 当n=2的时候，有2种方案，即每次迈1步和每次迈2步
        if (n == 2) {
            return 2;
        }

        return climbStairs(n-1) + climbStairs(n-2);
    }

}

package jzoffer;

/**
 * @Author: ZhangLingRan
 * @Description: 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * @DateTime: 2022/4/1 15:23
 */
public class offer16 {

    /**
     * O(N) 复杂度的算法超时了；
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double ans = 1;
        boolean flag = false;
        if (n < 0) {
            n = -n;
            flag = true;
        }
        for (int i = 0; i < n; i++) {
            ans = ans * x;
        }
        return flag ? 1/ans:ans;
    }


    /**
     * 考虑使用快速幂运算来计算 pow 函数
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {

        double res = 1;
        int absN = n;
        if (n < 0) {
            absN = -n;
        }
        while (absN != 0) {
            if ((absN & 1) == 1) {
                res *= x;
            }
            x *= x;
            absN >>>= 1;
        }
        return n >= 0 ? res : 1/res;
    }

    public static void main(String[] args) {
        offer16 obj = new offer16();
        double ans = obj.myPow2(-3, -2147483648);
        System.out.println(ans);
    }
}
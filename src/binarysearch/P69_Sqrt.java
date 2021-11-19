package binarysearch;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 不可使用内置函数
 * @author ZhangLingRan
 */
public class P69_Sqrt {

    /**
     * 二分查找算法使用
     * @param x
     * @return
     */
    public int mySqrt(int x) {

        double left = 0;
        double right = x*1.0;

        while (right - left > (1e-5)) {
            double mid = (left + right) / 2;
            if (mid*mid < x) {
                left = mid;
            }else {
                right = mid;
            }
        }

        return (int)right;
    }

}

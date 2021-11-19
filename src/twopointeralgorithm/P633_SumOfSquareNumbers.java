package twopointeralgorithm;


/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c
 * @author ZhangLingRan
 */
public class P633_SumOfSquareNumbers {


    /**
     * 思路：
     * a 从 0到根c
     * b 从 根c到0
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {

        // c的取值范围是：0 <= c <= 231 - 1
        // 那么，在公式 a * a + b * b 中，肯定会存在其和溢出的情况，那么其值为负，从而不断a++，一直到while循环结束，return false
        // int a = 0;
        // int b = (int) Math.sqrt(c);

        long a = 0;
        long b = (int) Math.sqrt(c);

        while (a <= b) {
            if (a * a + b * b == c) {
                return true;
            }else if (a * a + b * b > c) {
                --b;
            }else {
                ++a;
            }
        }

        return false;
    }

}

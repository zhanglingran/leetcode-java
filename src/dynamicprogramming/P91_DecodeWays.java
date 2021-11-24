package dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。题目数据保证答案肯定是一个 32 位 的整数。
 * @DateTime: 2021/11/24 22:50
 */
public class P91_DecodeWays {


    /**
     * 分析：
     *  首先对于s中的第i个位置的数字而言，如果在前i-1个字符中加上该元素，那么凑出来的解码个数就是 前i-1个数字的解码个数 再加上 前i-2个数字的解码个数
     *  再分析特殊情况，即新增的这个字符i，如果不在1-26之间，那么就不需要加上前i-1个数字的解码个数；如果s[i-1]s[i]两个数字组成的新数字不在1-26之间，
     *  那么前i-2个数字组成的解码个数就不用加到dp[i]中
     *  dp[i] = dp[i-1] + dp[i-2]
     * @param s
     * @return
     */
    public int numDecodings(String s) {

        int len = s.length();
        int[] dp = new int[len + 1];

        dp[0] = 1;

        char[] strs = s.toCharArray();

        for (int i = 1; i <= len; i++) {
            int a = 0;
            if (i-1 >= 0 && strs[i-1] != '0') {
                a = dp[i-1];
            }
            int b = 0;
            int tmp = (i-2 >= 0) ? ((strs[i-2] - '0') * 10 + strs[i-1] +  - '0') : 0;
            if (tmp <= 26 && tmp >= 10) {
                b = dp[i-2];
            }

            dp[i] = a + b;
        }

        return dp[len];
    }

    public static void main(String[] args) {
        P91_DecodeWays obj = new P91_DecodeWays();
        System.out.println(obj.numDecodings("123"));

    }

}

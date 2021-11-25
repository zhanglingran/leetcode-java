package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * @DateTime: 2021/11/25 22:12
 */
public class P1143_LongestCommonSubsequence {


    /**
     * dp[i][j] 表示str1的第i个位置之前的子串和str2第j个位置之前的子串之间的最长公共子序列的长度
     * dp[i][j] =
     *      if str1[i] == str2[j] then dp[i][j] = dp[i-1][j-1] + 1
     *      if str[i] != str2[j] then dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {

        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}

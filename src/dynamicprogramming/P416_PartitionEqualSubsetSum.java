package dynamicprogramming;

import java.util.Arrays;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * @DateTime: 2021/11/27 21:31
 */
public class P416_PartitionEqualSubsetSum {

    /**
     * 解题思路：
     *      1. 首先计算数组的和sum，若和是奇数，那么不可计算，直接返回即可
     *      2. 取和的一半，那么问题就会转化成：可否在nums数组中找到一些元素，这些元素之和为sum/2
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {

        int sum = 0;
        int len = nums.length;
        int max = 0;

        for (int i = 0; i < len; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        int val = sum / 2;

        if (len == 1 || sum % 2 == 1 || max > val) {
            return false;
        }


        // dp[i][j] 表示从 [0,i]个元素中，是否存在一组元素， 其和等于j
        boolean[][] dp = new boolean[len+1][val+1];

        dp[0][0] = true;
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }

        for (int j = 0; j <= val; j++) {
            for (int i = 1; i <= len; i++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = dp[i-1][j-nums[i-1]] | dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        return dp[len][val];
    }
}

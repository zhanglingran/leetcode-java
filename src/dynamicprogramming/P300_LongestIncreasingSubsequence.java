package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description:
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * @DateTime: 2021/11/25 14:46
 */
public class P300_LongestIncreasingSubsequence {


    /**
     * 分析：
     *  1. 子问题：
     *      数组长度为n-1的时候，最长的递增子序列长度是多少
     *  2. 状态转移
     *      dp[i] 表示以 nums[i] 结尾的子序列的最长递增序列的长度
     *      dp[i] = max{dp[k] + 1} 其中 k为 从0到i-1 （当 nums[i]>nums[k]的时候）
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        int res = 1;

        int[] dp = new int[nums.length];
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        P300_LongestIncreasingSubsequence obj = new P300_LongestIncreasingSubsequence();
        System.out.println(obj.lengthOfLIS(nums));
    }
}

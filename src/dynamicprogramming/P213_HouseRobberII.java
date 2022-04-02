package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * @DateTime: 2021/12/3 20:34
 */
public class P213_HouseRobberII {

    /**
     * 打家劫舍升级版，第一个和最后一个房子相连
     * dp[i] 表示偷第i个房子的时候的最大价值
     * dp[i] = max( dp[(i-1) % n] , dp[(i-2) % n] + val[i] ) 其中，
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;

        if (len <= 1) {
            return nums[0];
        }
        int[] dp = new int[len + 1];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[len-1];
    }

}

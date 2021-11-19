package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description:
 *      你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 *      如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
 *      一夜之内能够偷窃到的最高金额。
 * @DateTime: 2021/11/19 10:00
 */
public class P198_HouseRobberOpt {

    /**
     分析：f(n)表示偷取第n个房屋可以获得的最大金额是多少？
     每次走到第n个房间的时候：
     如果上一个房子没偷，当前的总金额为 f(n-2) + 当前房子的钱数
     如果上一个房子偷了，那么当前房子就不能偷，就是偷到上一个房子时的钱数
     f(n) = max{ f(n-2)+nums[n], f(n-1) }

     因为f(n) 只与f(n-1) 和 f(n-2)相关，那么可以将空间压缩

     */
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int pre1 = nums[0];
        int pre2 = Math.max(nums[0], nums[1]);
        int cur = 0;

        for (int i = 2; i < nums.length; i++) {
            cur = Math.max(pre1 + nums[i] , pre2);
            pre1 = pre2;
            pre2 = cur;
        }

        return cur;

    }
}

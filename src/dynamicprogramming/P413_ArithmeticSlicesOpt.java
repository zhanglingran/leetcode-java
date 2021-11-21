package dynamicprogramming;

/**
 * @Author: ZhangLingRan
 * @Description: 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *      现在给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * @DateTime: 2021/11/21 22:49
 */
public class P413_ArithmeticSlicesOpt {

    /**
     * 分析：
     *      如果加上第 i 个位置的元素，nums数组最后一段的满足等差的元素的个数是x个，记为f(x); 那么f(3)=1;f(4)=3;f(5)=6...即
     *      每次等差长度增加1,长度变成了x，那么相比于 等差数列个数为x-1的情况， 等差数列的个数 3位的多一个、4位的多一个、...、x位的多一个;
     *      即f(x) = f(x-1) + (x - 2) 其中要满足x-2大于0；
     * 优化：
     *      对空间进行优化，因为状态至于前一个相关，那么用两个变量即可完成；
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {

        if (nums.length < 3) {
            return 0;
        }

        int cur = 0;

        int sub = nums[1] - nums[0];

        int lastLen = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i-1] == sub) {
                lastLen++;
            }else {
                sub = nums[i] - nums[i-1];
                lastLen = 2;
            }
            cur += (lastLen < 3 ? 0 : (lastLen - 2));
        }

        return cur;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,5,5};
        P413_ArithmeticSlicesOpt obj = new P413_ArithmeticSlicesOpt();
        System.out.println(obj.numberOfArithmeticSlices(nums));

    }
}

package top_100;

/**
 * @Author: ZhangLingRan
 * @Description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @DateTime: 2021/11/26 10:46
 */
public class P42_TrappingRainWaterOp3 {


    /**
     * 解题方案三： 双指针方法
     * @param height
     * @return
     */
    public int trap(int[] height) {

        int len = height.length;

        int res = 0;
        int max = -1;

        int left = 0, right = len-1;

        for (int i = 0; i < len; i++) {
            max = Math.max(max, height[i]);
        }

        int cur = 0;

        while (left <= right){
            // 找到最大值之前，当前元素小于最大值，那么是从左向右遍历
            if (height[left] < max) {
                if (left == 0) {
                    cur = height[left];
                } else {
                    cur = Math.max(cur, height[left]);
                }
                res += (cur - height[left++]);
            // 找到最大值后，就从左向右遍历
            } else {
                if (right == len-1) {
                    cur = height[right];
                } else {
                    cur = Math.max(cur, height[right]);
                }
                res += (cur - height[right--]);
            }

        }
        return res;
    }
}

package top_100;

import java.util.Arrays;

/**
 * @Author: ZhangLingRan
 * @Description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @DateTime: 2021/11/26 10:46
 */
public class P42_TrappingRainWater {


    /**
     * 分析：
     *      数学计算方法，找到最高的一个柱子max，构成一个大矩形，然后分别减去多出来的部分，留下的就是雨水
     *
     * 实现思路：
     *      使用record数组记录从左到max柱子的递增序列；从右到max的递增序列；
     *      然后在最大矩形中减去（max-record[i]）和（柱子的高度height[i]）；最终得到的为雨水量
     *
     * 复杂度分析：
     *      时间复杂度：三个for 1 to len  →O(n)
     *      空间复杂度：开一个record数组   →O(n)
     * @param height
     * @return
     */
    public int trap(int[] height) {

        int len = height.length;
        int[] record = new int[len];

        int res = 0;
        int max = -1;

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                record[i] = height[i];
            } else {
                record[i] = Math.max(record[i-1], height[i]);
            }

            max = Math.max(max, height[i]);
        }

        for (int i = len-1; i >= 0; i--) {
            if (height[i] == max) {
                break;
            }

            if (i == len-1) {
                record[i] = height[i];
            } else {
                record[i] = Math.max(record[i+1], height[i]);
            }
        }

        res = max * len;


        for (int i = 0; i < len; i++) {
            res = res - (max - record[i]) - height[i];
        }

        return res;
    }
}

package top_100;

/**
 * @Author: ZhangLingRan
 * @Description:
 *      给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 *      垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * @DateTime: 2021/11/26 21:07
 */
public class P11_ContainerWithMostWaterOpt1 {

    /**
     * 方案：
     *      从左到右使用双指针遍历即可
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int len = height.length;
        int left = 0, right = len-1;
        int res = 0;
        int area = 0;
        while (left < right) {

            area = Math.min(height[left], height[right]) * (right-left);
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}

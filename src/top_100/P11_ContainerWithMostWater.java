package top_100;

/**
 * @Author: ZhangLingRan
 * @Description:
 *      给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 *      垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * @DateTime: 2021/11/26 21:07
 */
public class P11_ContainerWithMostWater {

    /**
     * 方案：
     *      找到从左到右的递增、和从右到左的递增序列；然后遍历并找到最大值；
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int len = height.length;
        int[] record = new int[len];

        int max = -1;
        int left = 0, right = len-1;

        for (int i = 0; i < len; i++) {

            if (i == 0) {
                record[i] = height[i];
            } else {
                record[i] = Math.max(record[i-1], height[i]);
            }
            max = Math.max(max, height[i]);
        }

        for (int i = len-1; i >= 0; i--) {

            if (max == height[i]){
                break;
            }

            if (i == len-1) {
                record[i] = height[i];
            } else {
                record[i] = Math.max(record[i+1], height[i]);
            }
        }

        int res = 0;
        while (left < right) {
            if (record[left] < record[right]) {
                res = Math.max(res, record[left] * (right-left));
                left++;
            } else {
                res = Math.max(res, record[right] * (right-left));
                right--;
            }
        }

        return res;
    }
}

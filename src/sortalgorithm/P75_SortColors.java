package sortalgorithm;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * @DateTime: 2021/11/4 19:18
 */
public class P75_SortColors {

    /**
     * 解题思路： 使用双指针，p0,p2分别表示指向的0和2的指针，首先 cur 指针从左向右移动，nums[cur] = 0 则交换道p0 =2则交换到p2
     * 注意：在nums[cur] == 2的时候，交换之后 cur 不会进行+1操作，是因为，cur和p2交换后，nums[cur]可能会变成0
     * @param nums
     */
    public void sortColors(int[] nums) {

        int len = nums.length;
        int p0 = 0, p2 = len - 1, cur = 0;

        // 如果 cur >= p2的时候，直接可以停止了
        while (cur <= p2) {
            if (nums[cur] == 0) {
                // 与nums[p0++] 交换
                swap(nums, cur++, p0++);
            } else if (nums[cur] == 2) {
                // 与nums[p2++] 交换
                swap(nums, cur, p2--);
            } else {
                cur++;
            }
        }
    }

    /**
     * 交换数组中 下标x和下标y这两个元素
     * @param nums
     * @param x
     * @param y
     */
    public void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

    public static void main(String[] args) {
        P75_SortColors obj = new P75_SortColors();
        int[] nums = {2, 0, 1};
        obj.sortColors(nums);

        for (int i = 0;i < nums.length; i++) {
            System.out.println(nums[i]);
        }


    }
}

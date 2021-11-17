package top_100;

/**
 * @Author: ZhangLingRan
 * @Description: 寻找两个正序数组的中位数
 * @DateTime: 2021/11/15 22:22
 */
public class P4_MedianOfTwoSortedArrays {

    /**
     * 即可以理解为，在两个数组中找到第k大的元素
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;

        int k = (len1 + len2) / 2;

        if ((len1 + len2) % 2 == 0) {
            return (findKthElemInTwoArray(nums1, nums2, len1, len2, k) + findKthElemInTwoArray(nums1, nums2, len1, len2, k + 1)) / 2;
        } else {
            return findKthElemInTwoArray(nums1, nums2, len1, len2, k + 1);
        }

    }

    private double findKthElemInTwoArray(int[] nums1, int[] nums2, int len1, int len2, int k) {


        // 标记两个数组的头部节点
        int pos1 = 0, pos2 = 0;
        while (k > 1) {
            int kth = k / 2, cur1, cur2;

            cur1 = pos1 + kth > len1 ? len1 : pos1 + kth;
            cur2 = pos2 + kth > len2 ? len2 : pos2 + kth;

            if (cur1 > 0 && cur2 > 0 &&nums1[cur1-1] > nums2[cur2-1]) {
                k -= (cur2 - pos2);
                pos2 = cur2;
            }else {
                k -= (cur1 - pos1);
                pos1 = cur1;
            }

            if (pos1 == len1) {
                return nums2[pos2 + k - 1];
            }
            if (pos2 == len2) {
                return nums1[pos1 + k - 1];
            }
        }

        return Math.min(nums1[pos1], nums2[pos2]);
    }

    public static void main(String[] args) {
        int[] num1 = {1, 3};
        int[] num2 = {2, 4};

        P4_MedianOfTwoSortedArrays obj = new P4_MedianOfTwoSortedArrays();
        System.out.println(obj.findMedianSortedArrays(num1, num2));
    }

}

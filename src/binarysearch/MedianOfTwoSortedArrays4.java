package binarysearch;

/**
 * @Author: ZhangLingRan
 * @Description: 寻找两个正序数组的中位数
 * @DateTime: 2021/10/25 13:19
 */
public class MedianOfTwoSortedArrays4 {

    /**
     * 查找两个有序数组的中位数, 合并数组后再查找,但是这样的话,算法的时间复杂度是O(n+m).可以继续优化到O(log(m+n))
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res = 0.0;

        int len1 = nums1.length, len2 = nums2.length;
        int k1 = 0, k2 = 0, k = 0;

        int[] merge = new int[len1 + len2];

        while (k1 < len1 && k2 < len2) {
            if (nums1[k1] <= nums2[k2]) {
                merge[k] = nums1[k1++];
            }else {
                merge[k] = nums2[k2++];
            }
            k++;
        }
        while (k1 < len1) {
            merge[k++] = nums1[k1++];
        }
        while (k2 < len2) {
            merge[k++] = nums2[k2++];
        }

        int mid = k >>> 1;
        if (k % 2 == 1) {
            return merge[mid];
        }else {
            return (merge[mid]*1.0 + merge[mid-1]*1.0) / 2;
        }
    }


    /**
     * 在两个数组中查找中位数,可以抽象成找两个数组的第k小元素, 思路:分别在两个数据中查找第k/2小的元素, 对比两个数组 删除最小的一组,并将k-k/2 不断迭代
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;

        int len = len1 + len2;
        int k = len >>> 1;

        // 如果是奇数,直接找到第k+1处的即可
        if (len % 2 == 1) {
            return findKthNum(nums1, nums2, k+1);
        }else {
            return (findKthNum(nums1, nums2, k)*1.0 + findKthNum(nums1, nums2, k+1)*1.0)/2;
        }

    }

    /**
     * 查找两个有序数组的第k大的元素
     * @param num1
     * @param num2
     * @param k
     * @return
     */
    public int findKthNum(int[] num1, int[] num2, int k) {
        int start1 = 0, start2 = 0;
        int len1 = num1.length;
        int len2 = num2.length;

        while (true) {

            if (start1 == len1) {
                return num2[start2+k-1];
            }
            if (start2 == len2) {
                return num1[start1+k-1];
            }
            if (k == 1) {
                return Math.min(num1[start1], num2[start2]);
            }

            // 正常情况下
            int half = k >>> 1;
            int newStart1 = Math.min(start1 + half, len1) - 1;
            int newStart2 = Math.min(start2 + half, len2) - 1;
            int pivot1 = num1[newStart1], pivot2 = num2[newStart2];
            if (pivot1 <= pivot2) {
                k -= (newStart1 - start1 + 1);
                start1 = newStart1 + 1;
            }else{
                k -= (newStart2 - start2 + 1);
                start2 = newStart2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays4 obj = new MedianOfTwoSortedArrays4();

        int[] nums2 = {1,3,4};
        int[] nums1 = {2};
        System.out.println(obj.findMedianSortedArrays1(nums1, nums2));
    }
}

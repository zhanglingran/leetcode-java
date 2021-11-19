package twopointeralgorithm;

import java.lang.reflect.Array;

/**
 * @author ZhangLingRan
 */
public class P88_MergeSortedArray {

    /**
     * 开一个新的数组，进行合并即可
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m+n];

        int pointer1 = 0, pointer2 = 0, k = 0;

        while (pointer1 < m && pointer1 < n) {
            if (nums1[pointer1] < nums2[pointer2]) {
                tmp[k++] = nums1[pointer1++];
            } else {
              tmp[k++] = nums2[pointer2++];
            }
        }
        while (pointer1 < m) {
            tmp[k++] = nums1[pointer1++];
        }
        while (pointer2 < n) {
            tmp[k++] = nums2[pointer2++];
        }

        for (int i = 0;i < m+n;i++) {
            nums1[i] = tmp[i];
        }
    }

    /**
     * 用逆向双指针，从后向前跑
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {

        int pointer1 = m - 1, pointer2 = n - 1;
        int end = m + n - 1;

        // 用三元运算符来优化一波
        while (pointer1 >= 0 && pointer2 >= 0) {
            nums1[end--] = nums1[pointer1] > nums2[pointer2] ? nums1[pointer1--] : nums2[pointer2--];
        }

        while (pointer1 >= 0) {
            nums1[end--] = nums1[pointer1--];
        }
        while (pointer2 >= 0) {
            nums1[end--] = nums2[pointer2--];
        }

    }

    /**
     * 代码重构，看起来比较高大上一点
     * 另外，直接用 m 和 n 作为单个数组的尾指针，不用再开一个变量了
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {

        int end = (m--) + (n--) - 1;
        // 用三元运算符来代替if判断语句，提高代码的逼格
        while (m >= 0 && n >= 0) {
            nums1[end--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (m >= 0) {
            nums1[end--] = nums1[m--];
        }
        while (n >= 0) {
            nums1[end--] = nums2[n--];
        }
    }

}

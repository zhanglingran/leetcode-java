package binarysearch;

import netscape.security.UserTarget;

/**
 * @题目名称 搜索旋转排序数组 II
 * @题目描述
 *  已知存在一个按非降序排列的整数数组 nums ，数组中的值互不相同。在传递给函数之前，
 *  nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为
 *  [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 *  例如， [0,1,2,4,5,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 *  给你旋转后的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 *  如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * @author ZhangLingRan
 */
public class P33_SearchInRotatedSortedArray {



    /**
     * 在经过一次旋转后的nums中查找target，二分查找之前需要先对数字分段
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        // 先找到逆序的点，再调用二分查找分别从两个子数组中查元素
        int pos = nums.length-1;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i-1]) {
                pos = i;
                break;
            }
        }

        int pos1 = binarySearch(nums, target, 0, pos);
        int pos2 = binarySearch(nums, target, pos, nums.length-1);

        if (pos1 < 0 && pos2 < 0) {
            return -1;
        }else {
            return pos1 < 0 ? pos2 : pos1;
        }
    }

    public static int binarySearch(int[] nums, int key, int fromIndex, int toIndex) {

        int low = fromIndex;
        int high = toIndex;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = nums[mid];

            if (key > midVal) {
                low = mid + 1;
            }else if (key < midVal){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return -(low+1);
    }


    /**
     * 官方题解：
     *  应用二分查找的思想，将旋转后的数组拆分后，必定有一个子数组是有序的，
     *  那么判断target是不是在有序数组的范围内，并调整 low 和 high
     * @param nums
     * @param target
     * @return
     */
    public static int search1(int[] nums, int target) {

        int low = 0;
        int high = nums.length-1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] == target) {
                return mid;
            }

            // 前半部分有序
            if (nums[low] <= nums[mid]) {
                // 在前半部分
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                }else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        int res = search1(nums, target);
    }
}

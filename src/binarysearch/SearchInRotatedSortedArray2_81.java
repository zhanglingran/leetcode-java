package binarysearch;

import com.sun.jmx.snmp.SnmpNull;

/**
 * @题目名称 搜索旋转排序数组 II
 * @题目描述
 *  已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。在传递给函数之前，
 *  nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为
 *  [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 *  例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 *
 *  给你旋转后的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 *  如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * @author ZhangLingRan
 */
public class SearchInRotatedSortedArray2_81 {

    /**
     * 在经过一次旋转后的nums中查找target，二分查找之前需要先对数字分段
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {

        int len = nums.length;
        int low = 0;
        int high = len - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] == target) {
                return true;
            }
            // 因为存在重复的元素，那么无法通过对比 num[0] 和 num[mid] 来对比是不是子序列有序
            if (nums[low] == nums[mid]) {
                low++;
            } else if (nums[low] < nums[mid]) {
                // 左边区间递增
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }else{
                // 在右边执行相同的操作【注意右边不一定是递增的】
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }

        }
        return false;
    }




}

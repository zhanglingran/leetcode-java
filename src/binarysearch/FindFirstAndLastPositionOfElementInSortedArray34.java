package binarysearch;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ZhangLingRan
 */
public class FindFirstAndLastPositionOfElementInSortedArray34 {

    /**
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }

        int len = nums.length;

        // 查找target所在位置
        int posRes = binarySearch(nums, 0, len, target);

        // 没找到target的位置，说明数组中没有要找的数，返回-1, -1
        if (posRes < 0 || nums[posRes] != target) {
            return new int[]{-1, -1};
        }else{

            // 找到res中 target 的最左和最右边的端点
            int left = posRes;
            int right = posRes;

            // 返回的是 (left, right) 注意是开区间哦~
            while (nums[left] == target){
                left--;
            }
            while (nums[right] == target){
                right++;
            }

            return new int[]{left+1, right-1};
        }
    }

    public static int binarySearch(int[] arrays, int fromIndex, int toIndex, int key) {

        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {

            // >>> 在Java中表示 逻辑右移操作|即无论原数是正还是负，都会在最高位补0（即负数逻辑右移会变成正数）
            int mid = (low + high) >>> 1;
            int midVal = arrays[mid];

            if (midVal < key) {
                low = mid+1;
            }else if (midVal > key) {
                high = mid-1;
            }else {
                return mid;
            }
        }
        // 保证low=0的时候，找不到位置元素的话可以返回一个小于0的数
        return -(low + 1);
    }

    //////////////////////////////官方题解///////////////////////////////
    /**
     * 分别通过二分查找，找到target的两个端点
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange1(int[] nums, int target) {

        if (nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }

        int len = nums.length;

        // 【找到右端点】查找大于target的元素所在位置
        int posRes1 = binarySearch1(nums, 0, len, target) - 1;
        // 【找到左端点】查找大于 target-1 元素所在的位置
        int posRes2 = binarySearch1(nums, 0, len, target-1);

        // 未找到目标元素
        if (posRes2 == len || nums[posRes2] != target) {
            return new int[]{-1, -1};
        }

        return new int[]{posRes2, posRes1};
    }

    /**
     * 找到第一个大于 key的数字的下标, 当不存在大于key的元素的时候，返回toIndex
     * @param arrays
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    public static int binarySearch1(int[] arrays, int fromIndex, int toIndex, int key) {

        int low = fromIndex;
        int high = toIndex - 1;
        int ans = toIndex;

        // 写成 low<=high 的形式的时候，循环的结束条件就是 low 不再小于 high 了
        while (low <= high) {

            int mid = (low + high) >>> 1;
            int midVal = arrays[mid];

            if (midVal > key) {
                ans = mid;
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        // 保证low=0的时候，找不到位置元素的话可以返回一个小于0的数
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5};
        int target = 5;

//        int[] res = searchRange1(nums, target);
//        System.out.println(res[0] + " " + res[1]);
        int pos = binarySearch1(nums, 0, nums.length, target);
        System.out.println(nums[pos]);
    }
}

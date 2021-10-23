package binarysearch;

/**
 * @author ZhangLingRan
 * @date 2021年10月23
 * @description 给你一个不存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 */
public class FindMinimumInRotatedSortedArray153 {

    /**
     * 用二分法，如果左边是一个递增的序列或者单个元素，那么在左边取min
     * 如果左边不满足上述条件，那么右边必定是一个单调的序列，那么从右边找到min，
     * 然后调整high，再从左边的非递增序列中查找min，用上述同样的方法
     *
     * 其中 nums[low] <= nums[mid] 之所以用小于等于，是在子序列只有{2,1}两个元素的时候，两边的序列都可以说是有序的，
     * 那么计算得到的mid必与low相同，如果该处是小于，那么会进一步执行 high = mid-1,从而不会判断{2,1}中的1
     * 
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {

        int low = 0, high = nums.length-1;
        int minVal = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            // 数组的左边是一个递增的序列或者单个元素
            if (nums[low] <= nums[mid]) {
                minVal = Math.min(minVal, nums[low]);
                low = mid + 1;
            }else {
                minVal = Math.min(minVal, nums[mid]);
                high = mid - 1;
            }
        }
        return minVal;
    }

    public static void main(String[] args) {
        int[] nums = {5,2};
        int res = findMin(nums);
        System.out.println(res);
    }

}

package binarysearch;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * @DateTime: 2021/10/23 22:57
 */
public class SingleElementInaSortedArray540 {

    /**
     * 解题思路：
     *      有序的数组（并不知道是增还是降），除了有一个元素是单个元素之外，其他每个元素都是2个
     *      那么当num[mid] 与 nums[mid+1]相等的时候，说明要找的元素在左边，否则在右边
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int low = 0, high = len-1;
        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
                  return nums[mid];
            } else if (mid % 2 == 0 && nums[mid] == nums[mid+1]) {
                low = mid + 2;
            } else if (mid % 2 == 1 && nums[mid] == nums[mid-1]) {
                low = mid + 1;
            } else if (mid % 2 == 1 && nums[mid] == nums[mid+1]) {
                high = mid - 1;
            } else if (mid % 2 == 0 && nums[mid] == nums[mid-1]) {
                high = mid - 2;
            }
        }
        return -1;
    }


    /**
     * 官方题解：
     *    数组长度必为奇数，且首尾坐标也都是偶数，那么取中间节点mid = (low + high) >>> 1
     *    如果 mid 是偶数 且nums[mid] = nums[mid+1] 说明mid左边全是数对,要找的元素在右边
     *                  且nums[mid] != nums[mid+1] 说明mid右边全是数对,要找的元素在左边或者就是mid
     *
     *
     *
     *    如果 mid 是偶数 mid右移一位，将mid变成奇数，进行下边的操作
     *    如果 mid 是奇数 且nums[mid] = nums[mid+1] 说明mid右边全是数对,要找的元素在左边
     *                      high = mid - 1;
     *                  且nums[mid] != nums[mid+1] 说明mid左边全是数对,要找的元素在右边
     *                      low = mid + 1;
     *
     * @param nums
     * @return
     * 代码进一步优化完成！10月23号进行修正
     */
    public int singleNonDuplicate1(int[] nums) {

        int len = nums.length;

        int low = 0, high = len-1;
        while (low < high) {
            int mid = (low + high) >>> 1;

            // 如果 mid 是偶数，那么将其往右边移动一位，此时mid是奇数
            if (mid % 2 == 0) {
                mid++;
            }

            if (nums[mid] == nums[mid+1]) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return nums[low];
    }


    /**
     * 通过异或来实现， 算法时间复杂度是O(n) 比较慢，但是思路很棒
     * 在nums不是一个有序序列的时候，使用该方法是一个不错的选择
     * @param nums
     * @return
     */
    public int singleNonDuplicate2(int[] nums) {

        int len = nums.length;

        int res = nums[0];
        for (int i = 1; i < len; ++i) {
            res ^= nums[i];
        }
        return res;
    }



}

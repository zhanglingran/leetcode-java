package dynamicprogramming;

import com.sun.jmx.snmp.SnmpNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: ZhangLingRan
 * @Description:
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * @DateTime: 2021/11/25 14:46
 */
public class P300_LongestIncreasingSubsequenceOpt {


    /**
     * 优化：
     *  使用dp[i]来表示以第nums[i]结尾的子序列的最长子序列：
     *  即从头到尾遍历nums数组，将nums[i]在dp[]中查找，如果nums[i]大于dp最后一个元素，将其加入到dp数组中
     *  如果nums[i]在dp数组之内，使用nums[i]替换掉dp数组中比nums[i]大的最小元素；
     *  如此，就可以在最终返回dp的长度，即最长的子序列；同时也可以得到最长的子序列是谁；
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        List<Integer> dp = new ArrayList<>(nums.length);
        dp.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            if (key > dp.get(dp.size()-1)) {
                dp.add(key);
            } else {
                int idx = Collections.binarySearch(dp, key);

                // idx < 0 表示：未找到该元素，并返回(-(insertion point) - 1) 其中insertion point为要插入该元素时，应该插入的位置；
                // 那么 -(idx + 1)， 返回的就是 key这个元素应该插入的位置，直接将其替换即可
                if (idx < 0) {
                    idx = -(idx + 1);
                    dp.set(idx, key);
                }
            }
        }

        return dp.size();
    }


    public static void main(String[] args) {
        int[] nums = {2,5,3};
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%d ", nums[i]);
        }
        System.out.println();
//        nums数组在排序后为：2 3 5
//        二分查找4的返回值：-3
        System.out.println(Collections.binarySearch(Arrays.stream(nums).boxed().collect(Collectors.toList()), 4));

//        P300_LongestIncreasingSubsequenceOpt obj = new P300_LongestIncreasingSubsequenceOpt();
//        System.out.println(obj.lengthOfLIS(nums));
    }
}

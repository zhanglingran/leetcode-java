import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/7 23:29
 */
public class LC446 {

    /**
     * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
     * 请你找出所有出现 两次 的整数，并以数组形式返回。你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题
     */

    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int val : nums) {
            int idx = (val - 1) % n;
            nums[idx] += n;
            if (nums[idx] > 2*n) {
                res.add(idx + 1);
            }
        }

        return res;
    }

}

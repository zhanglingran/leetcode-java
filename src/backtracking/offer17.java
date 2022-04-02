package backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/2 10:13
 */
public class offer17 {

    /**
     * 方案一：暴力解法，不适用于大数
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int nums = 0;
        while (n > 0) {
            nums = nums * 10 + 9;
            n--;
        }

        int[] ret = new int[nums];
        for (int i = 1; i <= n; i++) {
            ret[i-1] = i;
        }
        return ret;
    }

    /**
     * 方案二：大数解法：通过 0-9 的全排列来实现
     * @param n
     * @return
     */
    public int[] printNumbers1(int n) {
        // 将
        int[] nums = new int[10];
        for (int i = 0; i <= 9; i++) {
            nums[i] = i;
        }
        List<Integer> ret = new ArrayList<>();

        int[] path = new int[n];
        dfs(n, ret, nums, path, 0);
        nums = new int[ret.size()];
        for (int i = 0;i < ret.size();i++) {
            nums[i] = ret.get(i);
        }


        return nums;
    }

    /**
     * 与之前的不一样，这里使用的 path 是数组，这就不需要进行回溯了呀，否则还得回溯；
     * @param n
     * @param ret
     * @param nums
     * @param path
     * @param k
     */
    private void dfs(int n, List<Integer> ret, int[] nums, int[] path, int k) {
        if (k == n) {
            int res = 0;
            for (int num : path) {
                res = res * 10 + num;
            }
            if (res != 0) {
                ret.add(res);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            path[k] = nums[i];
            dfs(n, ret, nums, path, k+1);
        }
    }
}

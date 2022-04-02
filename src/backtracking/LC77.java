package backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
 * @DateTime: 2022/4/1 19:54
 */
public class LC77 {

    public List<List<Integer>> combine(int n, int k) {

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }

        List<List<Integer>> res = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<>();
        dfs(res, nums, 0, path, k, n);

        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, int cur, Deque<Integer> path, int k, int n) {

        // 剪枝：当剩余的部分无法满足k个数字的要求时，剪掉后续操作；
        if (path.size() + n - cur < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i < n; i++) {
            path.addLast(nums[i]);
            dfs(res, nums, i+1, path, k, n);
            path.removeLast();
        }
    }
}

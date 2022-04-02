package backtracking;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/1 19:49
 */
public class LC39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        /** 先排序，然后再通过排序后的数据进行剪枝操作 */
        Arrays.sort(candidates);

        dfs(res, path, candidates, target, 0, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] candidates, int target, int cur, int sum) {

        // 剪枝
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            sum += candidates[i];
            dfs(res, path, candidates, target, i, sum);
            path.removeLast();
            sum -= candidates[i];
        }
    }

    /**
     * 将 target 利用起来，不需要使用 sum 来记录和了
     * @param res
     * @param path
     * @param candidates
     * @param target
     * @param cur
     */
    private void dfs2(List<List<Integer>> res, Deque<Integer> path, int[] candidates, int target, int cur) {

        // 剪枝
        if (target < 0) {
            return;
        }
        if (0 == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i < candidates.length; i++) {
            /** 剪枝操作：将后续不满足的部分剪掉 */
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            dfs2(res, path, candidates, target - candidates[i], i);
            path.removeLast();
        }
    }

}

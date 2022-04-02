package backtracking;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/1 22:12
 */
public class LC40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);

        // 使用 vis 数组标记去重
        boolean[] vis = new boolean[candidates.length];
        dfs(res, path, candidates, target, 0, vis);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] candidates, int target, int cur, boolean[] vis) {

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i < candidates.length; i++) {
            // 剪枝
            if (target - candidates[i] < 0) {
                break;
            }

            // 如果当前元素被访问过，那么后续将不再执行；
            // 如果当前元素未被访问，且其前边的相同元素也未被访问过，
            // 说明找个元素与上个元素在递归树上不再一个分支上或者不是直接的父子关系，那么就不能将其放入结果集，这会引起结果集重复
            if (vis[i] || (i > 0 && candidates[i] == candidates[i-1] && !vis[i-1])) {
                continue;
            }

            path.addLast(candidates[i]);
            vis[i] = true;
            dfs(res, path, candidates, target-candidates[i], i+1, vis);
            path.removeLast();
            vis[i] = false;
        }

    }
}

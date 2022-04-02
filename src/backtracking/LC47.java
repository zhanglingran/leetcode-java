package backtracking;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * @DateTime: 2022/4/1 23:24
 */
public class LC47 {

    public List<List<Integer>> permuteUnique(int[] nums) {


        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] vis = new boolean[nums.length];

        Arrays.sort(nums);

        dfs(res, path, vis, nums);

        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, boolean[] vis, int[] nums) {

        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // 去重
            if (vis[i] || (i > 0 && nums[i] == nums[i-1] && !vis[i-1])) {
                continue;
            }

            path.addLast(nums[i]);
            vis[i] = true;
            dfs(res, path, vis, nums);
            vis[i] = false;
            path.removeLast();

        }
    }

}

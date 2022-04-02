package backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * @DateTime: 2022/4/1 23:05
 */
public class LC46 {

    /**
     * 给定一个不含重复数字的数组，返回其全部可能的排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        boolean[] vis = new boolean[nums.length];
        dfs(res, path, nums, vis);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] nums, boolean[] vis) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            path.addLast(nums[i]);
            dfs(res, path, nums, vis);
            path.removeLast();
            vis[i] = false;
        }
    }

    public static void main(String[] args) {
        LC46 obj = new LC46();
        obj.permute(new int[]{1,2,3});
    }

}

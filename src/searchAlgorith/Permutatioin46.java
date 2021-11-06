package searchAlgorith;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: ZhangLingRan
 * @Description: 数组的全排列问题
 * @DateTime: 2021/11/5 21:12
 */
public class Permutatioin46 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        dfs(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, res);
        return res;
    }

    private void dfs(List<Integer> nums, int k, List<List<Integer>> res) {

        int n = nums.size();
        if (k == n - 1){
            res.add(new ArrayList<>(nums));
            return;
        }

        for (int i = k; i < n; i++) {
            Collections.swap(nums, i, k);
            dfs(nums, k+1, res);
            // 回溯
            Collections.swap(nums, i, k);
        }

    }


    ///////////////////////////////////////////方法二//////////////////////////////////////////

    /**
     * 使用标记数组和path数组来实现
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {

        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] vis = new boolean[nums.length];

        dfs1(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, len, res, path, vis);
        return res;
    }

    private void dfs1(List<Integer> nums, int k, int n, List<List<Integer>> res, Deque<Integer> path, boolean[] vis) {

        if (path.size() == n){
            res.add(new ArrayList<>(path));
            return;
        }

        // dfs1(...,k,...) 表示要为第k个地方选择元素
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }
            path.addLast(nums.get(i));
            vis[i] = true;
            dfs1(nums, k+1, n, res, path, vis);
            // 回溯
            path.removeLast();
            vis[i] = false;
        }

    }


    public static void main(String[] args) {
        Permutatioin46 obj = new Permutatioin46();
        int[] nums = {1,2,3};


        List<List<Integer>> res = obj.permute1(nums);
        for (List<Integer> list : res) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d ", list.get(i));
            }
            System.out.println();
        }
    }
}

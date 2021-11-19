package searchAlgorith;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: ZhangLingRan
 * @Description: 数组的全排列问题
 * @DateTime: 2021/11/5 21:12
 */
public class P47_PermutatioinII {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        int len = nums.length;
        Deque<Integer> path = new ArrayDeque<>();

        // 因为数据是一个 [-10, 10]的数，那么我们为了标记这个数组是不是被访问过，可以对数据+10后，映射到[0, 20]区间上
        boolean[] vis = new boolean[len];
        Arrays.sort(nums);
        dfs(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, len, path, vis, res);
        return res;
    }


    public void dfs(List<Integer> nums, int k, int len, Deque<Integer> path, boolean[] vis, List<List<Integer>> res) {
        if (len == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i =0; i < len; i++) {
            // 每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」
            if (vis[i] || (i > 0 && nums.get(i).equals(nums.get(i-1)) && !vis[i-1])) {
                continue;
            }
            int tmp = nums.get(i);

            path.addLast(tmp);
            vis[i] = true;
            dfs(nums, k + 1, len, path, vis, res);
            vis[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        P47_PermutatioinII obj = new P47_PermutatioinII();
        int[] nums = {1,1,2};


        List<List<Integer>> res = obj.permute(nums);
        for (List<Integer> list : res) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d ", list.get(i));
            }
            System.out.println();
        }
    }
}

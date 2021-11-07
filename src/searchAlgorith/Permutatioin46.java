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

        // 存储结果
        List<List<Integer>> res = new ArrayList();
        // 数组的长度
        int len = nums.length;
        // 从第 0 个位置开始填
        dfs(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, len, res);

        return res;
    }

    /**
     * 表示将元素填充到cur处
     * @param nums 给定的待排列数组
     * @param cur  当前需要填充的位置
     * @param len  数组的长度
     * @param res  结果集
     */
    public void dfs(List<Integer> nums, int cur, int len, List<List<Integer>> res) {

        // 递归的结束条件：当cur等于len-1的时候，表示已经填到了末尾了，此时将结果记录下来
        if (cur == len - 1) {
            res.add(new ArrayList(nums));
            return;
        }

        for (int i = cur; i < len; i++) {
            // 交换之后，表示使用nums数组的第i个位置的元素填充某种排列的第cur个位置(将第i和第cur位置的元素互换)
            Collections.swap(nums, i, cur);
            // 接下来就递归的填充某排列的第cur+1位置
            dfs(nums, cur+1, len, res);
            // 回溯，即换回原来的排列顺序
            Collections.swap(nums, i, cur);
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

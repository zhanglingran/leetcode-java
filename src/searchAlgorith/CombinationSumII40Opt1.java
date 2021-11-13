package searchAlgorith;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description:
 * @DateTime: 2021/11/13 21:51
 * 该题最最重要的点在于去重操作
 */
public class CombinationSumII40Opt1 {


    /**
     * 参考之前做的带有重复元素的组合问题，在此基础上，添加一个求和参数，并将其作为一个递归出口。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] vis = new boolean[candidates.length];

        Arrays.sort(candidates);

        Integer sum = 0;
        dfs(candidates, 0, sum, target, path, res, vis);

        return res;
    }

    /**
     * 从头向后访问未被标记的，如果之和为target就将其放到res中
     * @param candidates 数组
     * @param idx        当前递归填充第idx位置
     * @param sum        进行到当前，选中的数字之和
     * @param target     题中给出的元素之和
     * @param path       加和为target的列表
     * @param res        返回列表
     * @param vis        访问标记
     */
    private void dfs(int[] candidates, int idx, Integer sum, int target, Deque<Integer> path, List<List<Integer>> res, boolean[] vis) {

        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 已经走到了当前idx处，那么从[idx, len-1]中选择一个元素加入sum，并判断是否可以满足条件
        for (int i = idx; i < candidates.length; i++) {

            // 主要优化的策略：即如果sum加上当前候选后比target要大，那么循环后边的将全部跳过！
            if (sum + candidates[i] > target) {
                break;
            }

            // 此处是借鉴了有重复元素的全排列处理方案
            if (vis[i] || (i > 0 && candidates[i] == candidates[i-1] && !vis[i-1])) {
                continue;
            }

            sum += candidates[i];
            path.add(candidates[i]);
            vis[i] = true;
            dfs(candidates, i+1, sum, target, path, res, vis);
            vis[i] = false;
            path.removeLast();
            sum -= candidates[i];

        }
    }

    public static void main(String[] args) {
        CombinationSumII40Opt1 obj = new CombinationSumII40Opt1();

        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;

        List<List<Integer>> res = obj.combinationSum2(candidates, target);
        for (List<Integer> list : res) {
            for (Integer in : list) {
                System.out.printf("%d,", in);
            }
            System.out.println();
        }
    }
}

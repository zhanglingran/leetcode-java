package searchAlgorith;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合.
 *
 * @DateTime: 2021/11/13 20:50
 */
public class CombinationSumIII216 {


    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Integer sum = 0;
        dfs(1, 9, k, n, res, path, sum);
        return res;
    }

    /**
     *
     * @param idx   当前递归要填充第idx位置的元素{此处，如果是一个数组，那么需要搞个标记数组进行标记访问过的元素}
     * @param total 标记从1-9
     * @param k     每个结果List的长度
     * @param sum   list之和为sum
     * @param res   返回的结果集
     * @param path  每个满足条件的组合
     */
    private void dfs(int idx, int total, int k, int target, List<List<Integer>> res, Deque<Integer> path, Integer sum) {

        if (sum + idx > target) {
            return;
        }

        if (path.size() + (total - idx + 1) < k) {
            return;
        }

        // 满足path长度为k且其和为sum的时候
        /** 这里可以进行一步优化，如果当sum == target的时候，再往后走必然不能找到sum == target的结果了！因此，此时不论是否构成了结果集，一定要返回（剪枝提效）*/
        // 修改后  程序运行效率提高到0ms，打败了100%的用户
        if (sum == target) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = idx; i <= total; i++) {
            path.add(i);
            sum += i;
            dfs(i + 1, total, k, target, res, path, sum);
            sum -= i;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumIII216 obj = new CombinationSumIII216();
        int k = 3;
        int n = 9;
        List<List<Integer>> res = obj.combinationSum3(k, n);

        for (List<Integer> list : res) {
            for (Integer in : list) {
                System.out.printf("%d,", in);
            }
            System.out.println();
        }

    }

}

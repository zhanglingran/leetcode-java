package searchAlgorith;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: ZhangLingRan
 * @Description: 全排列问题
 * @DateTime: 2021/11/13 19:04
 */
public class Test {
    /**
     * 从1到n中返回所有可能的k个数字的组合
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, n, k, path, res);


        return res;
    }

    /**
     * 当前进行到了第idx, 递归的填充第idx处的元素
     * @param idx
     * @param n
     * @param k
     * @param path
     * @param res
     */
    private void dfs(int idx, int n, int k, Deque<Integer> path, List<List<Integer>> res) {

        // 剪枝
        if (path.size() + (n-idx+1) < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i <= n; i++) {
            path.add(i);
            dfs(i+1, n, k, path, res);
            path.removeLast();
        }
    }


    public static void main(String[] args) {
        Test obj = new Test();
        List<List<Integer>> res = obj.combine(4, 2);
        for (List<Integer> list : res) {
            for (Integer in : list) {
                System.out.printf("%d,", in);
            }
            System.out.println();
        }
    }

}

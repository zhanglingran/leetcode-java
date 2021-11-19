package searchAlgorith;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * @DateTime: 2021/11/6 21:33
 */
public class P77_Combinations {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int cur = 0;

        dfs(n, k, cur, res, path);
        return res;
    }

    /**
     * 将cur指针处的元素填到path中
     */
    private void dfs(int n, int k, int cur, List<List<Integer>> res, Deque<Integer> path) {

        // 剪枝, 可将运行效率从18ms提高道2ms
        if (path.size() + (n-cur+1) < k) {
            return;
        }


        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 从0开始循环，一直到n，每次在递归头处选择一个起始位置，递归体处理后边位置的元素~ （有点顿悟！）
        for (int i = cur; i < n; i++) {
            path.addLast(i + 1);
            dfs(n, k, i+1, res, path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        P77_Combinations obj = new P77_Combinations();
        List<List<Integer>> res = obj.combine(4, 2);
        for (List<Integer> list : res) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d ", list.get(i));
            }
            System.out.println();
        }
    }

}

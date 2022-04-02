package weekly.contest270;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 找出 3 位偶数, 该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。
 * @DateTime: 2021/12/5 10:31
 */
public class P5942_Finding3DigitEvenNumbers {

    /**
     * 组合问题，并且组合数字为偶数
     * @param digits
     * @return
     */
    public int[] findEvenNumbers(int[] digits) {


        Arrays.sort(digits);

        int len = digits.length;
        boolean[] vis = new boolean[len];
        Set<Integer> res = new HashSet<>();
        Deque<Integer> path = new ArrayDeque<>(3);
        dfs(digits, len, res, vis, 3, path);

        int[] ans = new int[res.size()];
        int i = 0;
        for (Integer elem: res) {
            ans[i++] = elem;
        }

        Arrays.sort(ans);
        return ans;

    }

    private void dfs(int[] digits, int len, Set<Integer> res, boolean[] vis, int k, Deque<Integer> path) {
        if (path.size() == k) {
            int sum = 0;
            for (Integer elem: path) {
                sum = sum * 10 + elem;
            }
            if (sum >= 100 && sum % 2 == 0){
                res.add(sum);
            }

            return ;
        }

        for (int i = 0; i < len; i++) {
            if (vis[i] || (i > 0 && digits[i] == digits[i-1] && vis[i-1])) {
                continue;
            }

            path.addLast(digits[i]);
            vis[i] = true;
            dfs(digits, len, res, vis, k, path);
            vis[i] = false;
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        P5942_Finding3DigitEvenNumbers obj = new P5942_Finding3DigitEvenNumbers();

        int[] nums = {2,1,3,0};
        obj.findEvenNumbers(nums);
    }
}


//[102,120,130,132,210,230,302,310,312,320]
//[102,120,130,132,210,230,302,310,312,320]
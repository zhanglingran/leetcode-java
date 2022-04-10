package contest288;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: ZhangLingRan
 * @Description:
 *      给你一个非负整数数组 nums 和一个整数 k 。每次操作，你可以选择 nums 中 任一 元素并将它 增加 1 。
 *      请你返回 至多 k 次操作后，能得到的 nums的 最大乘积 。由于答案可能很大，请你将答案对 109 + 7 取余后返回。
 * @DateTime: 2022/4/10 12:06
 */
public class P3 {


    /**
     * 将数据放到 优先队列中， 每次只为最小值+1， 经过k次操作后，数组元素之积即为最大值
     * @param nums
     * @param k
     * @return
     */
    public int maximumProduct(int[] nums, int k) {

        final int MOD = 1000000000+7;

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int num : nums) {
            queue.offer(num);
        }

        while (k > 0) {
            queue.offer(queue.poll() + 1);
            k--;
        }

        int res = 1;
        while (!queue.isEmpty()) {
            res = (int) ((1L * res * queue.poll()) % MOD);
        }

        return res;
    }

}

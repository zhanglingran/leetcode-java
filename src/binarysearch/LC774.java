import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/5/10 10:24
 */
public class LC774 {

    /*
    整数数组 stations 表示 水平数轴 上各个加油站的位置。给你一个整数 k 。
    请你在数轴上增设 k 个加油站，新增加油站可以位于 水平数轴 上的任意位置，而不必放在整数位置上。
    设 penalty() 是：增设 k 个新加油站后，相邻 两个加油站间的最大距离。
    请你返回 penalty() 可能的最小值。与实际答案误差在 10-6 范围内的答案将被视作正确答案。
     */

    public double minmaxGasDist(int[] stations, int k) {
        PriorityQueue<Double> queue = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });

        for (int i = 1; i < stations.length; i++) {
            queue.offer((double) (stations[i]-stations[i-1]));
        }

        for (int i = 0; i < k; i++) {
            double top = queue.poll();
            queue.offer(top/2);
            queue.offer(top/2);
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        LC774 obj = new LC774();
        int[] nums = new int[]{10,19,25,27,56,63,70,87,96,97};
        System.out.println(obj.minmaxGasDist(nums, 3));
    }

}

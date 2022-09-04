import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/15 9:31
 */
public class Offer59I {

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int[] ans = new int[nums.length - k + 1];

        int start = 0;
        int end = 0;
        while (end < ans.length && end < k) {
            queue.offer(nums[end++]);
        }

        while (end < nums.length && start < end) {
            ans[start] = queue.peek();
            queue.remove(nums[start]);
            queue.offer(nums[end++]);
            start++;
        }


        return ans;
    }

    public static void main(String[] args) {
        new Offer59I().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }
}

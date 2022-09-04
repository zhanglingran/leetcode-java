package array;
import javafx.util.Pair;

import javax.sound.midi.MidiFileFormat;
import java.util.Stack;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * @DateTime: 2022/4/1 10:40
 */
public class Leetcode416 {

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }

        int target = sum>>1;
        if (sum - target != target) {
            return false;
        }

        return dfs(nums, target);

    }

    public boolean dfs(int[] nums, int target) {
        int n = nums.length;
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        // 使用 pair 存储节点及其所在的位置；最终能够保证stack中存放的必然是组成和为 target 的子集
        stack.push(new Pair<>(0, -1));

        int sum = 0;
        int k = 0;

        while (!stack.isEmpty()) {
            sum += nums[k];
            if (sum == target) {
                stack.push(new Pair<>(nums[k], k));
                return true;
            } else if (sum > target) {
                // 将不要该元素了，并将k向后移动一个,并将nums[k] 从sum中减掉
                sum -= nums[k];
                k++;
                if (k >= n) {
                    Pair<Integer, Integer> p = stack.pop();
                    sum -= p.getKey();
                    k = p.getValue() + 1;
                }
            } else {
                stack.push(new Pair<>(nums[k], k));
                k++;
                if (k >= n) {
                    Pair<Integer, Integer> p = stack.pop();
                    sum -= p.getKey();
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode416 obj = new Leetcode416();
        int[] nums = {7,4,6,13,20,8};
        int target = 18;
        System.out.println(obj.dfs(nums, target));;
    }

}
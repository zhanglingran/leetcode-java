package top_100;

import javafx.util.Pair;

import java.util.Stack;

/**
 * @Author: ZhangLingRan
 * @Description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @DateTime: 2021/11/26 10:46
 */
public class P42_TrappingRainWaterOpt1 {


    /**
     * 分析：（解题方案二——使用单调栈来实现）
     *      单调栈：保证栈中的元素序列是单调的！
     *      在本题中，如果入栈元素比栈顶元素大，则栈顶元素出栈，并将通过当前元素与栈顶（出栈后的栈顶）元素来计算水量。
     * @param height
     * @return
     */
    public int trap(int[] height) {

        int len = height.length;
        // 模拟单调栈来使用；
        Stack<Pair<Integer, Integer>> stack = new Stack<>();

        // 结果集
        int res = 0;

        stack.push(new Pair(height[0], 0));

        for (int i = 1; i < len; i++) {
            while (height[i] > stack.peek().getKey()) {
                Pair<Integer, Integer> popHeight = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 将当前的弹出来并计算这一层的水量, 需要直到i和栈顶第二个元素的位置！
                int tmpHeight = Math.min(stack.peek().getKey(), height[i]) - popHeight.getKey();
                int tmpWidth = i - (stack.peek().getValue() + 1);
                res += tmpHeight * tmpWidth;
            }
            stack.push(new Pair(height[i], i));
        }

        return res;
    }

    public static void main(String[] args) {
        int[] height = {4,2,0,3,2,5};

        P42_TrappingRainWaterOpt1 obj = new P42_TrappingRainWaterOpt1();
        System.out.println(obj.trap(height));
    }
}

package PDD;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/29 19:00
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        resolve(arr, N);
    }

    private static void resolve(int[] arr, int n) {
        // 计算差值并放入map中
        Map<Integer, Integer> map = new HashMap(n-1);
        // flag 标记是不是存在重复数据
        boolean flag = true;
        // 记录最大最小值
        int maxV = Integer.MIN_VALUE;
        int minV = Integer.MAX_VALUE;

        // 判断是不是满足条件1，存在重复
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(arr[i] - arr[i-1]);
            if (map.containsKey(diff)) {
                map.put(diff, map.get(diff)+1);
                flag = false;
            } else {
                map.put(diff, 1);
            }

            maxV = Math.max(maxV, diff);
            minV = Math.min(minV, diff);
        }

        // 记录最大的重复次数
        int maxRepeat = Integer.MIN_VALUE;
        // 记录不存在的个数
        int notContain = 0;

        // 如果存在重复，无需判断条件2
        if (flag != false) {
            for (int k = minV; k <= maxV; k++) {
                if (!map.containsKey(k)) {
                    flag = false;
                    notContain++;
                }
                maxRepeat = Math.max(maxRepeat, map.get(k));
            }
        }

        if (flag) {
            System.out.println("Yes");
            System.out.println(minV + "," + maxV);
        } else {
            System.out.println("No");
            System.out.println(maxRepeat +","+notContain);
        }

    }

}

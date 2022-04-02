package ali;

import sortalgorithm.SortAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/3/25 9:48
 */
public class Minutes {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();


        for (int i = 0; i < t; i++) {
            long[] arr = new long[5];
            for (int j = 0; j < 5; j++) {
                arr[j] = in.nextLong();
            }
            Arrays.sort(arr);
            System.out.println(Math.min(arr[2], arr[0]+arr[1]));
        }
    }
}

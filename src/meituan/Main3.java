import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/16 10:25
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();


        if (n == 0 || m == 0) {
            System.out.println(0);
            return;
        }

        int[][] poss = new int[m][2];

        Map<Integer, Integer> map = new HashMap<>(n);

        for (int i = 0; i < m; i++) {
            poss[i][0] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            poss[i][1] = scanner.nextInt();
        }

        Arrays.sort(poss, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });


        System.out.println(n);

    }

}

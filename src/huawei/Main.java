import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/6 20:06
 */
public class Main {


    public static void main(String[] args) {
        int N, M;
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = scan.next();
            String[] num = input.split(",");
            for (int j = 0; j < num.length; j++) {
                // num[j] 为 i 的前驱
                map[Integer.valueOf(num[j])][i] = 1;
            }
        }

        // 建图后，通过广搜？还是深搜？计算
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(0);

        Deque<Integer> path = new ArrayDeque<>();

        while (queue.size() > 0) {
            int node = queue.getLast();
            path.addLast(node);
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if (i == M) {
                    flag = true;
                    break;
                }
                if (map[node][i] == 1 && count(map, i, N) == 1) {
                    queue.addLast(i);
                    map[node][i] = 0;
                }
            }
            if (flag) {
                break;
            }
        }

        while (path.size() > 0) {
            System.out.printf("%d ", path.getFirst());
            path.removeFirst();
        }
    }

    /**
     * 计算节点的入度 即找到该node列中1的数量
     * @param map
     * @param node
     * @return
     */
    public static int count(int[][] map, int node,int N) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            count += map[i][node];
        }
        return count;
    }

}


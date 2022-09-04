import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/16 16:30
 */
public class Main12 {


    private int vexSize;
    private int[] vexs;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;
    private boolean[] isVis;

    public Main12(int vexSize) {
        this.vexSize = vexSize;
        this.matrix = new int[vexSize][vexSize];
        this.vexs = new int[vexSize];

        for (int i = 0; i < vexSize; i++) {
            vexs[i] = i;
        }

        for (int i = 0; i < vexSize; i++) {
            for (int j = 0; j < vexSize; j++) {
                matrix[i][j] = INF;
            }
        }

        isVis = new boolean[vexSize];
    }

    public void prim() {

        int[] dis = matrix[0];

        int sum = 0;

        for (int i = 1; i < vexSize; i++) {
            int min = INF;
            int minId = 0;

            for (int j = 1; j < vexSize; j++) {
                if (dis[j] > 0 && dis[j] < INF && dis[j] < min) {
                    min = dis[j];
                    minId = j;
                }
            }

            dis[minId] = 0;

            sum += min;

            for (int k = 1; k < vexSize; k++) {
                if (dis[k] > 0 && dis[k] > matrix[minId][k]) {
                    dis[k] = matrix[minId][k];
                }
            }
        }

        System.out.println(sum);
    }


    public static void main(String[] args) {

    }
}

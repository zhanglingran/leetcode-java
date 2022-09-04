import javafx.util.Pair;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/26 21:49
 */
public class Dijkstra {

    /**
     * 边数量
     */
    private int mEdgNum;
    /**
     * 顶点集合
     */
    private char[] mVexs;
    /**
     * 邻接矩阵, 连通的点为权值，不连通则为INF，自身为0
     */
    private int[][] mMatrix;
    /**
     * 最大值
     */
    private static final int INF = Integer.MAX_VALUE;


    public Dijkstra(int mEdgNum, char[] mVexs, int[][] mMatrix) {
        this.mEdgNum = mEdgNum;
        this.mVexs = mVexs;
        this.mMatrix = mMatrix;
    }

    /**
     * 计算顶点 vs 到达其他节点的最短路径
     * @param prev 前驱顶点数组。
     * @return
     */
    public List<Integer>[] getShortPath(int vs, int[] prev, int[] dist) {

        // 标记已经找到了最短路径了
        boolean[] flag = new boolean[mVexs.length];

        // 初始化
        for (int i = 0; i < mVexs.length; i++) {
            flag[i] = false;
            prev[i] = 0;
            // 将dist[i]存放初始情况下 源点到各个节点的值
            dist[i] = mMatrix[vs][i];
        }

        // 对 源点vs 进行初始化操作
        flag[vs] = true;
        dist[vs] = 0;

        List[] paths = new List[mVexs.length];
        paths[0] = new ArrayList<>(0);

        for (int i = 1; i < mVexs.length; i++) {
            // 找到当前最小的路径的节点k
            int k = 0;
            int min = INF;
            for (int j = 0; j < mVexs.length; j++) {
                if (!flag[j] && min > dist[j]) {
                    min = dist[j];
                    k = j;
                }
            }

            // 将 k 进行标记
            flag[k] = true;

            // 找到与k相连的点并更新路径权值
            for (int j = 0; j < mVexs.length; j++) {
                int tmp = mMatrix[k][j] == INF ? INF : (min + mMatrix[k][j]);
                if (!flag[j] && tmp < dist[j]) {
                    dist[j] = tmp;
                    prev[j] = k;
                }
            }

            // 找到源点到达一个节点的最短路后，直接通过prev将路径保存下来
            Deque<Integer> que = new ArrayDeque<>();

            int node = k;
            while (node != 0) {
                que.addLast(node);
                node = prev[node];
            }
            que.addLast(vs);

            // paths[k] 记录从 vs 到达 k 节点的路径
            paths[k] = new ArrayList<>(que);
        }
        return paths;
    }

    /**
     * 计算顶点 vs 到达其他节点的最短路径, 使用优先队列进行优化
     * @param prev 前驱顶点数组。
     * @return
     */
    public List<Integer>[] getShortPathUsePriorityQueue(int vs, int[] prev, int[] dist) {

        List<Integer>[] paths = new List[mVexs.length];

        boolean[] isMark = new boolean[mVexs.length];

        // 优先队列保存的其实就是：vs到j节点的路径最短值
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });

        // 进行初始化
        for (int i = 0; i < mVexs.length; i++) {
            isMark[i] = false;
            dist[i] = mMatrix[vs][i];
            prev[i] = vs;
            if (mMatrix[vs][i] != 0 && mMatrix[vs][i] != INF) {
                priorityQueue.add(new Pair<>(i, mMatrix[vs][i]));
            }
        }

        // 将 vs 标记
        isMark[vs] = true;
        dist[vs] = 0;

        // 接下来找到最小边权的节点，并将其mark
        for (int i = 1; i < mVexs.length; i++) {

            Pair p = priorityQueue.poll();

            // k 就是与结果集相连的具有最小边权的点
            int k = (int) p.getKey();
            // min 就是最小边权
            int min = (int) p.getValue();

            for (int j = 0; j < mVexs.length; j++) {
                // 如果与k相连的节点的边权值不是INF， 那么将该权值 + min 就是j节点到vs的路径值
                int tmp = mMatrix[k][j] == INF ? INF : min + mMatrix[k][j];
                if (!isMark[j] && tmp < dist[j]) {
                    dist[j] = tmp;
                    priorityQueue.add(new Pair<>(j, tmp));
                    prev[j] = k;
                }
            }

            // 记录路径
            Deque<Integer> d = new ArrayDeque<>();
            int node = k;
            while (node != 0) {
                d.addLast(node);
                node = prev[node];
            }
            d.addLast(vs);

            paths[k] = new ArrayList<>(d);
        }

        return paths;
    }

    public static void main(String[] args) {

        char[] mVexs = new char[]{'A','B','C','D','E','F'};
        int mEdgNum = 8;
        int[][] mMatrix = new int[][]{
                {0, 10, 15, INF, INF, INF},
                {INF, 0, INF, 12, INF, 15},
                {INF, INF, 0, INF, 10, INF},
                {INF, INF, INF, 0, 2, 1},
                {INF, INF, INF, INF, 0, INF},
                {INF, INF, INF, INF, 5, 0},
        };

        Dijkstra obj = new Dijkstra(mEdgNum, mVexs,mMatrix);

        int[] prev = new int[mVexs.length];
        int[] dist = new int[mVexs.length];
        List<Integer>[] paths = obj.getShortPathUsePriorityQueue(0, prev, dist);

        // 输出vs到其他节点的最短路径
        for (int i = 1; i < mVexs.length; i++) {
            System.out.printf("(");
            for (int j = paths[i].size()-1; j > 0; j--) {
                System.out.printf("%c -> ", mVexs[paths[i].get(j)]);
            }
            System.out.println(mVexs[paths[i].get(0)] + ") = " + dist[i]);
        }
    }
}


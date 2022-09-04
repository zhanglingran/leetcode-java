import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/5/2 19:08
 */
public class Astar {

    /**
     * 地图的行列数
     */
    private int ROW = 3;
    private int COL = 3;


    /**
     * 8个方向
     */
    private static final int[][] dirction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    private static final int INF = Integer.MAX_VALUE;

    public void astar(Node start, Node end) {

        List<Node> closeSet = new ArrayList<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        openSet.add(start);
        while (!openSet.isEmpty()) {

            Node top = openSet.poll();

            if (top.equals(end)) {
                // 求出路径并将路径返回
            }

            // 对 top 节点的每个子节点，进行操作

            for (int i = 0; i < dirction.length; i++) {
                int xx = top.x + dirction[i][0];
                int yy = top.x + dirction[i][1];

                Node node = new Node(xx, yy, INF);

                if (checkValidate(xx, yy) && !closeSet.contains(node) && !openSet.contains(node)) {
                    // 计算 node 的估计值 并将其插入到 openSet 中
                } else if (openSet.contains(node)) {
                    //
                }

            }

        }

    }


    /**
     * 计算曼哈顿距离，求解最短路问题（将 Dijstra 升级为最佳优先算法）
     * @param start
     * @param end
     * @return
     */
    public int distance(Node start, Node end) {
        return Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
    }



    /**
     * 判断 这个位置是不是合法
     * @param xx
     * @param yy
     * @return
     */
    private boolean checkValidate(int xx, int yy) {
        if (xx < 0 || xx >= ROW || yy < 0 || yy >= COL) {
            return false;
        }
        return true;
    }


    class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}

import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/5/2 22:01
 */
public class DijkstraFindPath {

    class Node {
        int x;
        int y;
        double cost;
        double totalCost;

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

    /**
     * 初始化地图
     */
    Node[][] map = new Node[3][3];

    public void findPath(Node from, Node to) {


        // 获取邻居节点全部放到这里边？
        HashSet<Node> isMark = new HashSet<>();
        // 初始化操作


        // 获取最佳邻居节点

    }

}

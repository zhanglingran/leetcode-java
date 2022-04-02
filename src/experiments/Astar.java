package experiments;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: A*算法 解决八数码问题
 * @DateTime: 2021/12/20 14:18
 */
public class Astar {

    /**
     * 棋盘行数
     */
    public final static int ROW = 3;

    /**
     * 棋盘列数
     */
    public final static int COL = 3;

    /**
     * 用于距离初始化;
     */
    public final static int MAX_DISTANCE = Integer.MAX_VALUE;

    public final static int MAX_NUM = Integer.MAX_VALUE;

    /**
     * 用于存储初始状态
     */
    public static Node src = new Node(ROW, COL), dest = new Node(ROW, COL);

    /**
     * open_list集合
     */
    public static List<Node> openList = new ArrayList<>();

    /**
     * 判断OpenList集合是不是空
     * @return
     */
    public static boolean isEmpty() {
        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).dist != MAX_NUM){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个状态是否相同
     * @param index
     * @param digit
     * @return
     */
    public static boolean isEqual(int index, int digit[][]) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (openList.get(index).digit[i][j] != digit[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 打印节点的棋盘状态
     * @param node
     */
    public static void printNode(Node node) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.printf("%d ", node.digit[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 回溯打印搜索的路径
     * @param index
     */
    public static void printBySteps(int index) {
        // 用来记录路径;
        List<Node> paths = new ArrayList<>();
        paths.add(openList.get(index));
        index = openList.get(index).index;
        while (index != 0) {
            paths.add(openList.get(index));
            index = openList.get(index).index;
        }

        for (int i = paths.size() - 1; i >= 0; i--){
            System.out.println("Step " + (paths.size() - i));
            printNode(paths.get(i));
        }
    }

    /**
     * 使用第index个节点的状态来初始化节点
     * @param node
     * @param index
     */
    public static void initialNode(Node node, int index) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                node.digit[i][j] = openList.get(index).digit[i][j];
            }
        }
    }

    /**
     * 获取最小值节点
     * @return
     */
    public static int getMinNode() {
        int dist = MAX_NUM;

        // 用来记录最小节点在openList中的下标
        int loc = -1;
        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).dist == MAX_NUM) {
                continue;
            } else if ((openList.get(i).dist + openList.get(i).dep) < dist) {
                loc = i;
                dist = openList.get(i).dist + openList.get(i).dep;
            }
        }

        return loc;
    }

    /**
     * 判断是不是可以进行扩展;
     * 扩展结点，判断当前节点是不是存在于openList中，存在则返回false，即不可将此节点放到openList中去;
     * @param node
     * @return
     */
    public static boolean isExpandable(Node node) {
        for (int i = 0; i < openList.size(); i++) {
            if (isEqual(i, node.digit)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算两个状态之间的曼哈顿距离
     * @param node
     * @param digit
     * @return
     */
    public static int distOfManhattan(Node node, int digit[][]) {
        int distance = 0;
        boolean flag = false;
        for(int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                for (int k = 0; k < ROW; k++) {
                    for (int l = 0; l < COL; l++) {
                        if (node.digit[i][j] == digit[k][l]) {
                            distance += Math.abs(i - k) + Math.abs(j - l);
                            flag = true;
                            break;
                        } else {
                            flag = false;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
        }
        return distance;
    }


    /**
     * 计算两个状态之间的不在位棋子的数量
     * @param node
     * @param digit
     * @return
     */
    public static int distance(Node node, int digit[][]) {
        int distance = 0;
        for(int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (node.digit[i][j] != digit[i][j]) {
                    distance += 1;
                }
            }
        }
        return distance;
    }

    /**
     * 生成当前节点的子状态节点
     * @param index
     */
    public static void createSubNode(int index) {
        int x = 0, y = 0;
        boolean flag = false;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                // 找到棋盘中空格的位置(x,y)
                if (openList.get(index).digit[i][j] == 0) {
                    x =i; y = j;
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
            if(flag) {
                break;
            }
        }

        // 将空格向上移动，并判断移动之后的状态是否可以作为扩展节点放入OpenList中;
        Node node_up = new Node(ROW, COL);
        initialNode(node_up, index);
        int dist_up = MAX_DISTANCE;

        if (x > 0) {
            // swap函数 内联
            int tmp = node_up.digit[x][y];
            node_up.digit[x][y] = node_up.digit[x - 1][y];
            node_up.digit[x - 1][y] = tmp;

            if (isExpandable(node_up)) {
                dist_up = distOfManhattan(node_up, dest.digit);
                node_up.index = index;
                node_up.dist = dist_up;
                node_up.dep = openList.get(index).dep + 1;
                openList.add(node_up);
            }
        }

        // 将空格向下移动，并判断移动之后的状态是否可以作为扩展节点放入OpenList中;
        Node node_down = new Node(ROW, COL);
        initialNode(node_down, index);
        int dist_down = MAX_DISTANCE;
        if (x < 2) {
            int tmp = node_down.digit[x][y];
            node_down.digit[x][y] = node_down.digit[x + 1][y];
            node_down.digit[x + 1][y] = tmp;

            if (isExpandable(node_down)) {
                dist_down = distOfManhattan(node_down, dest.digit);
                node_down.index = index;
                node_down.dist = dist_down;
                node_down.dep = openList.get(index).dep + 1;
                openList.add(node_down);
            }
        }

        // 将空格向左移动，并判断移动之后的状态是否可以作为扩展节点放入OpenList中;
        Node node_left = new Node(ROW, COL);
        initialNode(node_left, index);
        int dist_left = MAX_DISTANCE;
        if (y > 0) {
            int tmp = node_left.digit[x][y];
            node_left.digit[x][y] = node_left.digit[x][y - 1];
            node_left.digit[x][y - 1] = tmp;

            if (isExpandable(node_left)) {
                dist_left = distOfManhattan(node_left, dest.digit);
                node_left.index = index;
                node_left.dist = dist_left;
                node_left.dep = openList.get(index).dep + 1;
                openList.add(node_left);
            }
        }

        // 将空格向右移动，并判断移动之后的状态是否可以作为扩展节点放入OpenList中;
        Node node_right = new Node(ROW, COL);
        initialNode(node_right, index);
        int dist_right = MAX_DISTANCE;
        if (y < 2) {
            int tmp = node_right.digit[x][y];
            node_right.digit[x][y] = node_right.digit[x][y + 1];
            node_right.digit[x][y + 1] = tmp;

            if (isExpandable(node_right)) {
                dist_right = distOfManhattan(node_right, dest.digit);
                node_right.index = index;
                node_right.dist = dist_right;
                node_right.dep = openList.get(index).dep + 1;
                openList.add(node_right);
            }
        }

        // 扩展结束后，将当前节点的距离设置为MAX_NUM，可以用来标记，当前节点是否已经访问过;
        openList.get(index).dist = MAX_NUM;
    }

    public static void main(String[] args) {

        src = new Node(ROW, COL);
        dest = new Node(ROW, COL);

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入初始状态:");
        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++) {
                src.digit[i][j] = scanner.nextInt();
            }
        }
        src.index = 0;
        src.dep = 1;

        System.out.println("输入目标状态:");

        for (int m = 0; m < ROW; m++) {
            for (int n = 0; n < COL; n++) {
                dest.digit[m][n] = scanner.nextInt();
            }
        }

        openList.add(src);

        while (true) {
            if (isEmpty()) {
                System.out.println("目标状态不可达，程序终止！");
                return;
            } else {
                // 最小值节点的位置
                int loc;
                loc = getMinNode();
                if(isEqual(loc, dest.digit)) {
                    System.out.println("初始状态:");
                    printNode(src);

                    printBySteps(loc);
                    System.out.println("执行成功!");
                    break;
                } else {
                    createSubNode(loc);
                }
            }
        }
    }
}


class Node{
    public int[][] digit;
    /**
     * 当前状态与目标状态之间的距离
     */
    public int dist;

    /**
     * 节点的深度
     */
    public int dep;

    /// 故：代价函数f = dist + dep.

    /**
     * 指向其父节点位置的指针,代替Closed_list的作用，来记录当前状态是从哪个状态转移过来的;
     */
    int index;

    public Node(int row, int col){
        this.digit = new int[row][col];
    }
}

// 2 8 3 1 6 4 7 0 5
// 1 2 3 8 0 4 7 6 5



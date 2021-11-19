package searchAlgorith;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ZhangLingRan
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 * 现在，我们可以将0变为1，以使两座岛连接起来，变成一座岛。
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）
 * @DateTime: 2021/11/8 20:07
 */
public class P934_ShortestBridge {

    private int[][] direct = {{0, 0, -1, 1}, {-1, 1, 0, 0}};

    /**
     * 先使用dfs标记grid中的其中一个岛屿, 访问过的点标记为-1或者任何不等于1和0的数字, 同时将该岛屿的周围海岸线记录在queue中。
     * 之后使用 bfs 来找这个岛屿的海岸线到另一个岛屿的路径，这里需要注意的是，使用bfs从海岸线一层层的往外扩充，直到触碰到海岸线为止
     * @param grid
     * @return
     */
    public int shortestBridge(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        // 先使用 dfs 标记grid中的第一个岛屿， 并将该岛屿的海岸线存放到queue中
        boolean flag = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = -1;
                    dfs(grid, i, j, queue);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        // 使用bfs，一层层的扩充海岸线来找到另一个岛屿（使用level来记录海岸线的扩充层数）
        Pair<Integer, Integer> pair;
        int level = 0;
        while (!queue.isEmpty()) {

            level++;
            int count = queue.size();

            while (count > 0) {
                pair = queue.remove();
                count--;

                for (int d = 0; d < direct[0].length; d++) {

                    int x = pair.getKey() + direct[0][d];
                    int y = pair.getValue() + direct[1][d];

                    if (x >= 0 && x < row && y >= 0 && y < col) {

                        if (grid[x][y] == -1) {
                            continue;
                        }
                        // 说明还是在岛上
                        if (grid[x][y] == 1) {
                            return level;
                        }
                        queue.add(new Pair<>(x, y));
                        grid[x][y] = -1;
                    }
                }
            }
        }
        return level;
    }


    /**
     * 使用dfs将grid中第一个岛屿标记出来， 同时返回其边缘的0
     *
     * @param grid
     * @param i
     * @param j
     */
    private void dfs(int[][] grid, int i, int j, Queue<Pair<Integer, Integer>> queue) {

        int m = grid.length;
        int n = grid[0].length;

        for (int d = 0; d < direct[0].length; d++) {

            int x = i + direct[0][d];
            int y = j + direct[1][d];

            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (grid[x][y] == 0) {
                    queue.add(new Pair<>(x, y));
                }
                if (grid[x][y] == 1){
                    grid[x][y] = -1;
                    dfs(grid, x, y, queue);
                }
            }
        }
    }

    public static void main(String[] args) {
        P934_ShortestBridge obj = new P934_ShortestBridge();

//        int[][] nums = {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
        int[][] nums = {{0,0,1,0,1},{0,1,1,0,1},{0,1,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(obj.shortestBridge(nums));

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.printf("%d ", nums[i][j]);
            }
            System.out.println();
        }
    }

}

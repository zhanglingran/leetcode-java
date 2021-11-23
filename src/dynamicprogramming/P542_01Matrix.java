package dynamicprogramming;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * @DateTime: 2021/11/22 13:52
 */
public class P542_01Matrix {

    /**
     * 从两个方向搜索，记录最小的即可
     * dp[i][j] 表示第ij个元素距离0最小的
     * if mat[i][j] = 0
     *      dp[i][j] = 0
     * else
     *      dp[i][j] = min{min{dp[i-1][j] + 1, dp[i][j-1] + 1}, dp[i][j]};
     * 即  每次只需考虑左上、右下两个方向的值，最终取最小值；
     *
     * 另外需要注意的是： 在两个方向上：会存在dp[i][j] + 1的情况，那么dp[i][j]的初始化需要调整一下！即不可用Integer.MAX_VALUE
     * 因为该最大值+1后会出现整数溢出；
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {

        int row = mat.length;
        int col = mat[0].length;

        if (row <= 2 || col <= 2) {
            return mat;
        }

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE-1;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i-1][j] + 1, dp[i][j]);
                    }
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+1);
                    }
                }
            }
        }

        for (int i = row-1; i >= 0; i--) {
            for (int j = col-1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (i < row-1) {
                        dp[i][j] = Math.min(dp[i+1][j] + 1, dp[i][j]);
                    }
                    if (j < col-1) {
                        dp[i][j] = Math.min( dp[i][j+1] + 1, dp[i][j]);
                    }
                }
            }
        }

        return dp;
    }

    private void bfs(int x, int y, int[][] dp, int row, int col, boolean[][] vis) {

        int[][] direct = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y));
        vis[x][y] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            while (size-- > 0) {
                Pair<Integer, Integer> pair = queue.remove();
                int px = pair.getKey();
                int py = pair.getValue();

                for (int k = 0; k < direct[0].length; k++) {
                    int xx = px + direct[0][k];
                    int yy = py + direct[1][k];
                    if (xx >= 0 & xx < row && yy >= 0 && yy < col && !vis[xx][yy] && dp[xx][yy] != 0) {
                        dp[xx][yy] = Math.min(level, dp[xx][yy]);
                        queue.add(new Pair<>(xx, yy));
                        vis[xx][yy] = true;
                    }
                }
            }
        }
    }

}

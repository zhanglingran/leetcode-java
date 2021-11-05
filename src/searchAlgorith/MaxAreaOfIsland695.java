package searchAlgorith;

/**
 * @Author: ZhangLingRan
 * Description: 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * DateTime: 2021/11/5 20:23
 */
public class MaxAreaOfIsland695 {

    public int maxAreaOfIsland(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;

    }

    /**
     * 从i,j处进行深度优先搜索, 找到一个岛屿的面积
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int dfs(int[][] grid, int i, int j) {

        if (grid[i][j] == 0) {
            return 0;
        }
        int[][] direct = {{0, 0, -1, 1},{-1, 1, 0, 0}};

        grid[i][j] = 0;
        int m = grid.length, n = grid[0].length;
        int area = 1;


        for (int k = 0; k < direct[0].length; k++) {
            int x = i + direct[0][k];
            int y = j + direct[1][k];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                area += dfs(grid, x, y);
            }
        }

        return area;
    }

    /// 方法二: 通过栈来计算

}

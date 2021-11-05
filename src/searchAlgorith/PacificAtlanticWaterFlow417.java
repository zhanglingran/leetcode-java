package searchAlgorith;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *               请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * @DateTime: 2021/11/5 20:43
 */
public class PacificAtlanticWaterFlow417 {

    /**
     * 逆向思维：从周围向上流，并记录四周边上的每个点可以流经的点
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {


        int m = heights.length, n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] toPacific = new boolean[m][n];
        boolean[][] toAtlantic = new boolean[m][n];

        // 遍历左右两边的点:左边太平洋，右边大西洋
        for (int i = 0; i < m; i++) {
            // 左边
            dfs(heights, toPacific, i, 0);
            // 右边
            dfs(heights, toAtlantic, i, n-1);
        }

        // 遍历上下两边: 上边太平洋， 下边大西洋
        for (int i = 0; i < n; i++) {
            // 上边
            dfs(heights, toPacific, 0, i);
            // 下边
            dfs(heights, toAtlantic, m-1, i);
        }

        // 遍历之后，可以得到toPacific和toAtlantic两个数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (toAtlantic[i][j] && toPacific[i][j]) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    res.add(pair);
                }
            }
        }

        return res;
    }

    private void dfs(int[][] heights, boolean[][] toOcean, int i, int j) {

        int m = heights.length, n = heights[0].length;

        if (toOcean[i][j]) {
            return;
        }
        toOcean[i][j] = true;
        int[][] direct = {{0, 0, -1, 1},{-1, 1, 0, 0}};
        for (int k = 0; k < direct[0].length; k++) {
            int x = i + direct[0][k];
            int y = j + direct[1][k];
            if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[i][j]) {
                dfs(heights, toOcean, x, y);
            }
        }

    }
}

package searchAlgorith;

import java.util.Stack;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个二维的 0-1 矩阵，如果第 (i, j) 位置是 1，则表示第 i 个人和第 j 个人是朋友。已知
 *               朋友关系是可以传递的，即如果 a 是 b 的朋友， b 是 c 的朋友，那么 a 和 c 也是朋友，换言之这
 *               三个人处于同一个朋友圈之内。求一共有多少个朋友圈。
 * @DateTime: 2021/11/4 20:07
 */
public class P547_NumberOfProvinces {

    /**
     * 解题方案：用深搜，记录个数
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int count = 0;

        int m = isConnected.length, n = isConnected[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    count++;
                    Stack<Integer> stack = new Stack<>();
                    isConnected[i][j] = 0;
                    stack.push(i);
                    while (!stack.empty()) {
                        int node = stack.pop();
                        for (int k = 0; k < n; k++) {
                            if (isConnected[node][k] == 1) {
                                isConnected[node][k] = 0;
                                stack.push(k);
                            }
                        }
                    }
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        P547_NumberOfProvinces obj = new P547_NumberOfProvinces();
        System.out.println(obj.findCircleNum(isConnected));
    }
}

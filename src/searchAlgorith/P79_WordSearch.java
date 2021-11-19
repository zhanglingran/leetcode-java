package searchAlgorith;

/**
 * @Author: ZhangLingRan
 * @Description: 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *               单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * @DateTime: 2021/11/7 17:22
 */
public class P79_WordSearch {

    private boolean result = false;

    /**
     * 解题思路：遍历board, 找到word[0]字符所在的位置(i, j), 并在此处开始检索, 每次向四个方向进行检索word[1]，找到的话将其标记，并递归找word[2]...
     * 如果找到最后发现不能找齐word字符串，那么进行回溯，并将之前的标记删除
     *
     * @param board 给定的一个网格，每个格子中有一个字符
     * @param word  需要检索的字符串（判断word字符串是不是存在于board中）
     * @return      返回判断结果，true or false
     */
    public boolean exist(char[][] board, String word) {

        int row = board.length;
        int col = board[0].length;
        boolean[][] vis = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0)) {
                    // word的第0个字符已经找到了，那么通过递归查找第1个字符
                    vis[i][j] = true;
                    if (dfs(board, word, i, j, 1, vis)) {
                        return true;
                    }
                    vis[i][j] = false;
                }
            }
        }

        return false;
    }

    /**
     * 从 posi, posj 处开始进行递归检索word是不是存在于board中
     * @param board
     * @param word
     * @param posi
     * @param posj
     * @param cur  本地递归是为了查找word的第cur个字符
     * @return
     */
    private boolean dfs(char[][] board, String word, int posi, int posj, int cur, boolean[][] vis) {

        // 递归出口
        if (cur == word.length()) {
            return true;
        }

        // 在board中的检索方向： 左 右 上 下
        int[][] direct = {{0, 0, -1, 1}, {-1, 1, 0, 0}};

        // board的长宽各是多少
        int row = board.length;
        int col = board[0].length;

        // 在四个方向上检索word的第cur个字符
        for (int i = 0; i < direct[0].length; i++) {
            int posx = posi + direct[0][i];
            int posy = posj + direct[1][i];
            if (posx >= 0 && posx < row && posy >= 0 && posy < col && !vis[posx][posy] && board[posx][posy] == word.charAt(cur)) {
                vis[posx][posy] = true;

                /// 注意：在递归中使用函数返回值的时候， 一定一定要留意这种写法
                if (dfs(board, word, posx, posy, cur + 1, vis)) {
                    return true;
                }
                vis[posx][posy] = false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        P79_WordSearch obj = new P79_WordSearch();

        char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        String word = "AAB";
        System.out.println(obj.exist(board, word));
    }
}

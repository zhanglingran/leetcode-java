import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/12 21:37
 */
public class Offer38 {

    /**
     * 全排列问题: 存在重复的情况
     * @param s
     * @return
     */
    public String[] permutation(String s) {

        char[] chars = s.toCharArray();

        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        boolean[] vis = new boolean[chars.length];

        Arrays.sort(chars);

        dfs(chars, path, res, vis);


        return res.toArray(new String[res.size()]);
    }

    private void dfs(char[] chars, StringBuilder path, List<String> res, boolean[] vis) {
        if (path.length() == chars.length) {

            res.add(path.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {

            if (vis[i] == true || (i > 0 && chars[i] == chars[i-1] && !vis[i-1])) {
                continue;
            }

            vis[i] = true;
            path.append(chars[i]);
            dfs(chars, path, res,  vis);
            path.deleteCharAt(path.length()-1);
            vis[i] = false;
        }
    }
}
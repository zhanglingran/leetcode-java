import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/12 22:08
 */
public class Offer38II {

    /**
     * 组合问题: 存在重复的情况
     *
     * @param s
     * @return
     */
    public String[] permutation(String s, int k) {

        char[] chars = s.toCharArray();

        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        Arrays.sort(chars);

        dfs(chars, path, res, 0, k);


        return res.toArray(new String[res.size()]);
    }

    private void dfs(char[] chars, StringBuilder path, List<String> res, int cur, int k) {
        if (path.length() == k) {
            res.add(path.toString());
            return;
        }

        for (int i = cur; i < chars.length; i++) {

            path.append(chars[i]);
            dfs(chars, path, res, i + 1, k);
            path.deleteCharAt(path.length() - 1);
        }
    }


    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "1234";
        System.out.println(str1.compareTo(str2));
    }
}

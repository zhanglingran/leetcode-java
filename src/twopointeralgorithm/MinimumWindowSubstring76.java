package twopointeralgorithm;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * @author ZhangLingRan
 */
public class MinimumWindowSubstring76 {

    /**
     * chars[] 表示在目前S的子串中，每个字符缺少的数量; flag[] 表示字符是否包含在T中
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        // ASCII码表共128个字符，其编码范围是0到127; 故用128大小的数组，足矣
        boolean[] flag = new boolean[128];
        int[] chars = new int[128];

        int t_len = t.length();
        int s_len = s.length();

        // 将T中的元素映射到数组chars和flag中，chars表示T串中各字母的个数，而flag表示T串中存在的字母;
        for (int i = 0; i < t_len; ++i) {
            ++chars[t.charAt(i)];
            flag[t.charAt(i)] = true;
        }

        // left 和 right 表示左右两个指针，其中两个指针必须满足: left <= right
        // cnt 表示S的子串中包含了T中元素的数量
        // max_left 标记左边指针的最大值,即最靠近右边指针的位置
        // min_size 表示S中最小子串的长度
        int left = 0;
        int cnt = 0;
        int max_left = 0;
        int min_size = s.length() + 1;

        for (int right = 0; right < s_len; ++right) {
            char c = s.charAt(right);
            if (flag[c]) {
                if (--chars[c] >= 0) {
                    ++cnt;
                }

                while (cnt == t_len) {
                    if (right - left + 1 < min_size) {
                        max_left = left;
                        min_size = right - left + 1;
                    }

                    // 如果子串左边的元素在T中，将该元素从子串中去掉后，如果子串中的字符不再包含全部的T中的字符，那么将cnt-1
                    if (flag[s.charAt(left)] && (++chars[s.charAt(left)] > 0)) {
                        --cnt;
                    }
                    ++left;
                }
            }
        }
        return min_size > s_len ? "" : s.substring(max_left, max_left + min_size);
    }
}

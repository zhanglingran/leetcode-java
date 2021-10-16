package twopointeralgorithm;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * @author ZhangLingRan
 */
public class MinimumWindowSubstring76 {

    /**
     * chars[] 表示在目前S的子串中，每个字符缺少的数量; flag[] 表示字符是否包含在T中
     *
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


    /**
     * 重写一遍，以供复习
     * 题目要求在s中找出一个最短的子串，该子串包含t中全部的元素——并无顺序要求
     * <p>
     * 思路：
     * 首先，将t中的字母及该字母出现的次数进行映射，以便查找。flag[i]表示字符i是否出现过，chars[i]表示i字符出现的次数
     * 映射完成之后，l-r组成滑动窗口，窗口中是s中包含了t中全部元素的子串，通过l指针向右移动来减少子串长度，以找出最短子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow_(String s, String t) {

        int[] chars = new int[128];
        boolean[] flags = new boolean[128];

        int sLength = s.length();
        int tLength = t.length();

        // 将t中的元素全部映射到flags和chars数组中来
        for (int i = 0; i < tLength; ++i) {
            char elem = t.charAt(i);
            flags[elem] = true;
            ++chars[elem];
        }


        int l = 0, max_l = 0, min_size = sLength + 1, cnt = 0;
        for (int r = 0; r < sLength; ++r) {
            char elem = s.charAt(r);

            if (flags[elem]) {
                if ((--chars[elem]) >= 0) {
                    cnt++;
                }

                while (cnt == tLength && l <= r) {
                    // 判断当前的子串长度是不是小于最短子串长度
                    if (r - l + 1 < min_size) {
                        min_size = r - l + 1;
                        max_l = l;
                    }
                    // 判断去掉滑动窗口左边的元素后，子串是否还包含了t中全部的元素,若不再包含，将cnt-1
                    if (flags[s.charAt(l)] && (++chars[s.charAt(l)]) > 0) {
                        --cnt;
                    }
                    ++l;
                }
            }

        }

        return min_size > sLength ? "" : s.substring(max_l, max_l + min_size);
    }


    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";

        MinimumWindowSubstring76 obj = new MinimumWindowSubstring76();
        String res = obj.minWindow_(s, t);

        System.out.println(res);

    }
}
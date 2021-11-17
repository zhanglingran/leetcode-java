package top_100;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2021/11/15 20:53
 */
public class P3_LongestSubstringWithoutRepeatingCharacters {


    /**
     * 使用set集合记录pre和cur双指针之间是否有重复的存在，每次pre向后移动一位，则set清空重新添加元素
     *
     * TODO : 该方式为暴力算法，复杂度很高，需要进行优化
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] strs = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int resMax = 0;
        for (int pre = 0; pre < strs.length; pre++) {
            set.clear();
            int cur = pre;
            while (cur < strs.length && !set.contains(strs[cur])) {
                set.add(strs[cur]);
                cur++;
            }
            resMax = Math.max(resMax, set.size());

        }
        return resMax;
    }



    /////////////////////////优化版本1  79ms -> 7ms/////////////////////////////

    /**
     * 优化版本: 从79ms 优化到了 9ms
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringOpt1(String s) {
        char[] strs = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int resMax = 0;

        int pre = 0, cur = 0;
        while (pre < strs.length && cur < strs.length) {
            while (set.contains(strs[cur])) {
                set.remove(strs[pre]);
                pre++;
            }
            set.add(strs[cur]);
            cur++;
            resMax = Math.max(resMax, set.size());
        }

        return resMax;
    }



    /////////////////////////优化版本2  题解的方案 : 时间也在7ms上下的样子/////////////////////////////

    /**
     * 优化版本2: 题解方案
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringOpt2(String s) {
        Set<Character> set = new HashSet<>();
        int resMax = 0, cur = -1, n = s.length();

        for (int pre = 0; pre < n; pre++) {
            if (pre > 0) {
                set.remove(s.charAt(pre - 1));
            }
            while (cur + 1 < n && !set.contains(s.charAt(cur + 1))) {
                set.add(s.charAt(cur + 1));
                cur++;
            }
            resMax = Math.max(resMax, cur + 1 - pre);
        }
        return resMax;
    }

    public static void main(String[] args) {
        Set<Character> s = new HashSet<>();
        String a = " ";
        char[] aa = a.toCharArray();
        for (int i = 0; i < aa.length; i++) {
            s.add(aa[i]);
            System.out.println("a=" + aa[i] + ",");
        }

        System.out.println(s.size());
    }

}

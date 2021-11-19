package twopointeralgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定字符串S，找到最多有k个不同字符的最长子串T。
 *
 * @author ZhangLingRan
 */
public class P340_LongestSubstringWithAtMostKDistinctCharacters {


    /**
     * 思路：一个for来遍历start，在for中用while来移动end直到窗口满足题意，每次loop来记录最大值maxLen
     *
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {

        if (s == null || k <= 0 || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>(26);
        int len = s.length();
        int end = 0;

        // 记录substring的最大长度
        int maxLen = 0;

        for (int start = 0; start < len; start++) {

            // 从start开始找
            char st = s.charAt(start);

            while (end < len && map.size() <= k) {
                char c = s.charAt(end);
                if (map.size() == k && !map.containsKey(c)){
                    break;
                }
                map.put(c, map.getOrDefault(c, 0) + 1);
                end++;
            }

            maxLen = Math.max(maxLen, end - start);

            // 将start向后移动一下，并将map中的key进行处理
            int currentCount = map.get(st) - 1;
            if (currentCount == 0) {
                map.remove(st);
            } else {
                map.put(st, currentCount);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "eceba";
        int k = 3;
        lengthOfLongestSubstringKDistinct(s, k);
    }

}

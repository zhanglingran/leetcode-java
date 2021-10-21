package twopointeralgorithm;

import java.util.List;

/**
 * 给一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * @author ZhangLingRan
 */
public class LongestWordInDictionaryThroughDeleting524 {

    public String findLongestWord(String s, List<String> dictionary) {
        int maxLen = Integer.MIN_VALUE;
        int pos = 0;

        int len = dictionary.size();
        for (int i = 0; i < len; ++i) {
            // 对每个dictionary[i] 和 s进行比对，返回相同且同序的字符个数
            int sameLen = compareStr(s, dictionary.get(i));
            if (sameLen > maxLen) {
                pos = i;
                maxLen = sameLen;
            } else if (sameLen == maxLen) {
                if (dictionary.get(pos).compareTo(dictionary.get(i))  > 0) {
                    pos = i;
                }
            }
        }
        return maxLen == Integer.MIN_VALUE ? "" : dictionary.get(pos);
    }

    /**
     * 记录s中与dictionary相同且同序的字符个数，同时判断dictionary是不是s的子序列
     * @param s
     * @param dictionary
     */
    private int compareStr(String s, String dictionary) {
        int sPointer = 0;
        int dictPointer = 0;
        int sLen = s.length();
        int dictLen = dictionary.length();
        // 判断是否可以通过删除s中的字符得到dictionary
        int count = 0;

        while (dictPointer < dictLen && sPointer < sLen) {
            if (s.charAt(sPointer) == dictionary.charAt(dictPointer)) {
                count++;
                sPointer++;
                dictPointer++;
            } else {
                sPointer++;
            }
        }
        // sPointer < sLen 说明dictionary不是s的子序列
        return dictPointer < dictLen ? Integer.MIN_VALUE : count;
    }



    ////////////////////////////////参考题解////////////////////////////////

    /**
     * 思路：
     *  1.判断是不是dict是不是s的子序列
     *  2.找到子序列最长的且字典序最小的dict
     *
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord1(String s, List<String> dictionary) {
        int maxLen = Integer.MIN_VALUE;
        String res = "";
        for (String dict : dictionary) {
            int dictP = 0, sP = 0;
            while (dictP < dict.length() && sP < s.length()) {
                if (dict.charAt(dictP) == s.charAt(sP)) {
                    ++dictP;
                }
                ++sP;
            }

            // 这个等于号说明，当dictP走到最后时，才能保证dict是s的子序列
            if (dictP == dict.length() ){
                if (dictP > res.length() || (dictP == res.length() && res.compareTo(dict) > 0)) {
                    res = dict;
                }
            }
        }
        return res;
    }


}

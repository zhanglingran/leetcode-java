package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定 s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 * @DateTime: 2021/11/25 14:15
 */
public class P139_WordBreak {

    /**
     * dp[i] : 以s[i]为结尾的子串s’可否由空格拆分为一个或多个在字典中出现的单词。可以的话dp[i]=true;不可以的话dp[i]=false
     * 那么 dp[i] = 在(dp[i-wordDict[j]])中存在true即可;
     *
     * 也是一个凑硬币问题的变形题目
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String str : set) {
                int sLen = str.length();
                if (i >= sLen && str.equals(s.substring(i-sLen, i))) {
                    dp[i] = dp[i] || dp[i-sLen];
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        P139_WordBreak obj = new P139_WordBreak();

        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        System.out.println(obj.wordBreak(s, wordDict));
    }

}

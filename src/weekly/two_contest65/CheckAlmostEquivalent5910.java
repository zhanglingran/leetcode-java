package weekly.two_contest65;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 双周赛
 * @DateTime: 2021/11/13 23:00
 */
public class CheckAlmostEquivalent5910 {

    public boolean checkAlmostEquivalent(String word1, String word2) {

        int n = word1.length();

        char[] words1 = word1.toCharArray();
        char[] words2 = word2.toCharArray();

        Set<Character> set = new HashSet<>();

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        Arrays.sort(words1);
        Arrays.sort(words2);

        int count1 = 1,count2 = 1;
        set.add(words1[0]);
        set.add(words2[0]);
        for (int i = 1; i < n; i++) {
            set.add(words1[i]);
            set.add(words2[i]);
            if (words1[i] == words1[i-1]) {
                count1++;
            }else {
                map1.put(words1[i-1], count1);
                count1 = 1;
            }

            if (words2[i] == words2[i-1]) {
                count2++;
            }else {
                map2.put(words2[i-1], count2);
                count2 = 1;
            }
        }

        map1.put(words1[n-1], count1);
        map2.put(words2[n-1], count2);


        for (Character c : set) {
            int num1 = map1.get(c) == null ? 0 : map1.get(c);
            int num2 = map2.get(c) == null ? 0 : map2.get(c);
            if (Math.abs(num1-num2) > 3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        CheckAlmostEquivalent5910 obj = new CheckAlmostEquivalent5910();

        String str1 = "cccddabba";
        String str2 = "babababab";


        System.out.println(obj.checkAlmostEquivalent(str1, str2));
    }
}

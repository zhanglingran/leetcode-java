package top_100;

import com.sun.jmx.snmp.SnmpNull;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description:
 *      给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *      给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * @DateTime: 2021/11/26 21:39
 */
public class P17_LetterCombinationsAfAPhoneNumber {

    /**
     * 依次弹出deque中的元素（字符串），并将新取字符对应的三个字母追加到该元素的后边（即取出一个进去对三个）
     * TODO 需要优化
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0 || digits.equals("") || digits.equals("1")) {
            return new ArrayList<>();
        }

        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        Deque<StringBuilder> deque = new ArrayDeque<>();
        char[] nums = digits.toCharArray();

        for (int i = 0; i < nums.length; i++) {
            String keys = map.get(nums[i]-'0');
            int cout = deque.size();
            if (cout == 0) {
                for (String c: keys.split("")) {
                    deque.add(new StringBuilder(c));
                }
            } else {
                while (cout-- > 0) {
                    StringBuilder str = deque.removeFirst();
                    for (String c: keys.split("")) {
                        StringBuilder ss = new StringBuilder(str);
                        deque.add(new StringBuilder(ss.append(c)));
                    }
                }

            }
        }

        List<String> list = new ArrayList<>();

        while (!deque.isEmpty()) {
            list.add(new String(deque.removeLast()));
        }

        return list;
    }

    public static void main(String[] args) {


        P17_LetterCombinationsAfAPhoneNumber obj = new P17_LetterCombinationsAfAPhoneNumber();
        List<String> list = obj.letterCombinations("2");
        for (String s: list) {
            System.out.println(s);
        }
    }
}

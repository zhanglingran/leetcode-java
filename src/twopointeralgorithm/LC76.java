import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/17 19:38
 */
public class LC76 {

    public String minWindow(String s, String t) {
        int tLen = t.length();
        int sLen = s.length();

        int[] chars = new int[128];
        boolean[] flag = new boolean[128];

        for (char c : t.toCharArray()) {
            chars[c]++;
            flag[c] = true;
        }

        int left = 0;
        int right = 0;
        int cnt = 0;
        int minLen = sLen + 1;
        int markLeft = 0;

        for (right = 0; right < sLen; right++) {
            char elem = s.charAt(right);
            // 如果当前元素存在于 t 中
            if (flag[elem]) {
                if (--chars[elem] >= 0) {
                    cnt++;
                }

                while (cnt == t.length() && left <= right) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        markLeft = left;
                    }

                    if (flag[s.charAt(left)] && ++chars[s.charAt(left)] > 0) {
                        cnt--;
                    }
                    left++;
                }
            }
        }

        return minLen > sLen ? "" : s.substring(markLeft, markLeft + minLen);
    }
}
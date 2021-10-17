package twopointeralgorithm;

/**
 * @author ZhangLingRan
 */
public class ValidPalindromeII680 {

    /**
     * left 和 right 向中间移动，其中一个不相同的时候，跳过left或者right后，都不满足即返回false
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {

        int len = s.length();

        int left = 0;
        int right = len-1;
        int cnt = 0;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                ++left;
                --right;
            }else {
                ++left;
                ++cnt;
            }

        }

        left = 0;
        right = len-1;
        int cnt1 = 0;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                ++left;
                --right;
            }else {
                --right;
                ++cnt1;
            }
            if (cnt1 > 1 && cnt > 1) {
                return false;
            }
        }

        return true;
    }


    /**
     * 题解：算法优化
     * @param s
     * @return
     */
    public boolean validPalindrome1(String s) {

        int len = s.length();
        int left = 0;
        int right = len-1;

        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                ++left;
                --right;
            }else {
                return valid(s.substring(left, right)) || valid(s.substring(left+1, right+1));
            }

        }
        return true;
    }

    /**
     * 判断是不是回文串
     * @param s
     * @return
     */
    private boolean valid(String s) {
        int len = s.length();
        int left = 0, right = len-1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
}

package weekly.contest263;

/**
 * @author Zhang L.R.
 */
public class P5902_CheckIfNumbersAreAscendingInASentence {

    /**
     * 检查字符串s中的数字是否满足递增
     * @param s
     * @return
     */
    public boolean areNumbersAscending(String s) {

        String[] ss = s.split(" ");
        int len = ss.length;
        int pre = 0;
        for (int i = 0; i < len; ++i) {
            // 判断是不是数字
            char num = ss[i].charAt(0);
            if (num >= '0' && num <= '9') {
                int number = Integer.valueOf(ss[i]);
                if (number > pre) {
                    pre = number;
                }else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "1 box has 3 blue 3 red 6 green and 12 yellow marbles";
        P5902_CheckIfNumbersAreAscendingInASentence obj = new P5902_CheckIfNumbersAreAscendingInASentence();
        boolean res = obj.areNumbersAscending(s);
        System.out.println(res);
    }
}

package ali;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/3/25 9:31
 */
public class Register {

    // 小红注册账号问题

    public static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String line= in.next();
            System.out.println(regist(line));
        }
    }


    public static String regist(String line) {
        int n = line.length();
        if (n > 12 || n < 6) {
            return "illegal length";
        }

        if (set.contains(line)) {
            return "acount existed";
        }

        // 判断是不是包含除了英文字符之外的数据
        if (isContainsAnotherCharacter(line.toUpperCase())) {
            return "illegal charactor";
        }
        set.add(line);

        return "registration complete";
    }

    private static boolean isContainsAnotherCharacter(String toUpperCase) {
        for (int i = 0; i < toUpperCase.length(); i++) {
            char c = toUpperCase.charAt(i);
            if (c < 'A' || c > 'Z') {
                return true;
            }
        }
        return false;
    }

}

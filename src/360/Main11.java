import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/16 15:34
 */
public class Main11 {

    private final static String OK = "OK";
    private final static String Irregular = "Irregular password";
    private final static int Length = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String pass = scanner.next();
            System.out.println(chackOk(pass));
        }
    }

    private static String chackOk(String pass) {

        if (pass.length() < Length) {
            return Irregular;
        }

        boolean containsNum = false;
        boolean containsLowerCase = false;
        boolean containsUpperCase = false;
        boolean containsSpacialCase = false;

        for (char c : pass.toCharArray()) {

            if (c >= 'a' && c <= 'z') {
                containsLowerCase = true;
            } else if (c >= 'A' && c <= 'Z') {
                containsUpperCase = true;
            } else if (c >= '0' && c <= '9') {
                containsNum = true;
            } else {
                containsSpacialCase = true;
            }

        }

        if (containsNum&&containsLowerCase&&containsUpperCase&&containsSpacialCase) {
            return OK;
        }
        return Irregular;
    }

}

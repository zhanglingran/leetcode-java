import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/16 10:08
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int ans = 0;
        for (int i = 0; i < str.length(); i++ ) {
            if (str.charAt(i) == ' ') {
                ans++;
            }
        }
        System.out.println(ans);
    }
}

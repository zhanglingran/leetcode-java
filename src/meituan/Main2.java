import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/16 10:14
 */
public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int h1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        int h2 = scanner.nextInt();
        int m2 = scanner.nextInt();

        System.out.println((h2-h1)*60 + m2-m1);

    }
}

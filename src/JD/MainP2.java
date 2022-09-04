import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/16 18:55
 */
public class MainP2 {

    public static void main(String[] args) {

        System.out.println(-1%-10);

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int k = 0; k < T; k++) {

            Stack<String> stack = new Stack<>();

            int n = scanner.nextInt();
            scanner.nextLine();

            if (n % 2 == 1) {
                System.out.println("No");
            } else {
                int i = 0;
                for (; i < n; i++) {
                    String line = scanner.nextLine();
                    String[] words = line.trim().split("\\s+");

                    if (words[0].equals("end")) {
                        if (stack.isEmpty()) {
                            break;
                        } else {
                            if (stack.peek().equals(words[words.length-1])) {
                                stack.pop();
                            } else {
                                break;
                            }
                        }
                    } else {
                        stack.push(words[0]);
                    }
                }

                if (stack.isEmpty()) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }

    }

}

import com.sun.scenario.animation.shared.ClipEnvelope;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/2 14:38
 */
public class Offer20 {

    public boolean isNumber(String s) {
        


        return true;
    }

    public static void main(String[] args) {
        String str = "1..";
        String[] ss = str.split("\\.");
        for (String s : ss) {
            System.out.println(s);
        }
        System.out.println(ss.length);
    }

}

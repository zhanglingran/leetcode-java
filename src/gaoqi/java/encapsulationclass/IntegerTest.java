package gaoqi.java.encapsulationclass;

/**
 * Integer 类会将 -128 ~ 127 之间的数字缓存到 cache[] 中，只要创建的Integer 对象的数字在此区间，那么都将会在同一个地址
 * @author ZhangLingRan
 */
public class IntegerTest {

    public static void main(String[] args) {


        Integer a = Integer.valueOf(-120);
        Integer b = Integer.valueOf(-120);

        // 返回值是true
        System.out.println(a == b);
// ————————————————————————————————————————————————————————————————————
        Integer c = Integer.valueOf(1200);
        Integer d = Integer.valueOf(1200);

        // 返回值是false
        System.out.println(c == d);
    }

}

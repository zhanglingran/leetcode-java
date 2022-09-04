package meituan111;

import java.util.*;


/**
 * @author ZhangLingRan
 */
public class Main {

    public static final int COUNT = 71000;
    public static List<Integer> map = new ArrayList<>(COUNT);
    public static void createTable() {
        for (int i = 0; i < COUNT; i++) {
            if (i < 10) {
                map.add(i, i);
            } else {
                int ith = map.get(i/10) ^ map.get(i%10);
                map.add(i, ith);
            }
        }
    }

    public static void main(String[] args) {
        var a = 1;
        var b = '5';
        System.out.println(a + b);
    }

}

package weekly.two_contest65;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2021/11/13 23:18
 */
class Robot5911 {

    private int[][] board;
    private int posx;
    private int posy;
    private String factTo;
    /**
     *  x, y: {1,0}为东; {0,1}为北; {-1,0}为西; {0,-1}为南
      */
    private static int[][] direct = {{1, 0, -1, 0},{0, 1, 0, -1}};

    private static Map<String, Integer> map = new HashMap<>();
    private static Map<Integer, String> map1 = new HashMap<>();

    static {
        map.put("East", 0);
        map.put("North", 1);
        map.put("West", 2);
        map.put("South", 3);

        map1.put(0, "East");
        map1.put(1, "North");
        map1.put(2, "West");
        map1.put(3, "South");
    }

    public Robot5911(int width, int height) {
        board = new int[width][height];
        posx = 0;
        posy = 0;
        factTo = "East";
    }

    public void move(int num) {
        int width = board.length;
        int height = board[0].length;

        int to = map.get(factTo);
        int tmp = num;
        num = num % ((width + height) * 2 - 4);
        // 完全是试出来的妈的
        if (num == 0 && num != tmp && posx == posy && posx == 0 && to == 0) {
            to = (to + 3) % 4;
        }

        for (int step = 0; step < num; ) {
            int x = posx + direct[0][to];
            int y = posy + direct[1][to];

            if (x >= 0 && x < width && y >= 0 && y < height) {
                posx = x;
                posy = y;
                step++;
            }else{
                to = (to+1) % 4;
            }
        }
        factTo = map1.get(to);
    }


    public int[] getPos() {
        return new int[]{posx, posy};
    }

    public String getDir() {
        return factTo;
    }

    public static void main(String[] args) {
        Robot5911 robot = new Robot5911(97, 98);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String opt = scanner.next();
            switch (opt) {
                case "move":
                    robot.move(scanner.nextInt());
                    break;
                case "getPos":
                    int pos[] = robot.getPos();
                    System.out.println("["+pos[0]+","+pos[1]+"]");
                    break;
                case "getDir":
                    System.out.println(robot.getDir());
                    break;
                case "Robot":
                    robot = new Robot5911(scanner.nextInt(), scanner.nextInt());

            }
        }
    }
}


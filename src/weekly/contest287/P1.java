package contest287;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/3 10:33
 */
public class P1 {


    public int convertTime(String current, String correct) {

        // 找到两个时间之间的差值
        String[] time1 = current.split(":");
        String[] time2 = correct.split(":");

        int hours = Integer.valueOf(time2[0]) - Integer.valueOf(time1[0]);
        int minute = Integer.valueOf(time2[1]) - Integer.valueOf(time1[1]);

        int totalMinute = Math.abs(hours * 60 + minute);

        // 凑硬币
        int[] slice = new int[]{60, 15, 5, 1};

        int ans = 0;
        for (int i = 0; i < slice.length; i++) {
            int times = totalMinute / slice[i];
            totalMinute = totalMinute % slice[i];
            ans += times;
            if (totalMinute <= 0) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        P1 obj = new P1();
        int ans  = obj.convertTime("09:41", "10:34");
        System.out.println(ans);
    }
}

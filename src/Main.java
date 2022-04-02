import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2021/11/30 15:24
 */
public class Main {


    public int findDuplicate(int[] nums) {

        int fast = 0, slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);

        slow = 0;

        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }

        return slow;
    }


    public static void main(String[] args) {

        Main obj = new Main();
        int[] nums = {2,5,9,6,9,};
        obj.findDuplicate(nums);

    }

}



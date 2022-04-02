package sortalgorithm;

import com.sun.jmx.snmp.SnmpNull;

/**
 * @Author: ZhangLingRan
 * @Description: 快速排序算法
 * @DateTime: 2021/10/25 16:40
 */
public class SortAlgorithm {

    /**
     * 实现快速排序
     * @param nums
     */
    public static void quickSort(int[] nums, int low, int high) {

        if (low < high) {
            int pos = partition(nums, low, high);
            quickSort(nums, low, pos - 1);
            quickSort(nums, pos + 1, high);
        }
    }


    public static int partition(int[] nums, int low, int high) {

        int pivot = nums[low];
        while (low < high) {

            while (low < high && nums[high] >= pivot){
                high--;
            }
            nums[low] = nums[high];

            while (low < high && nums[low] <= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    // 将两个函数写到一起

    public static void quickSort1(int[] nums, int low, int high) {

        if (low >= high) {
            return;
        }
        int left = low, right = high;
        int key = nums[left];

        while (left < right) {

            while (left < right && nums[right] >= key){
                right--;
            }
            nums[left] = nums[right];

            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;

        quickSort(nums, low, left - 1);
        quickSort(nums, left + 1, high);
    }


    /**
     * @TODO 堆排序
     * @param nums
     */
    public static void headSort(int[] nums) {

    }


    public static void main(String[] args) {
        int[] nums = {4,1,2,5,7,6,0,3};
        quickSort1(nums, 0, nums.length-1);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }


    }
}

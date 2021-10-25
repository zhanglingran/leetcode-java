package sortalgorithm;

/**
 * @Author: ZhangLingRan
 * @Description: 数组中的第K个最大元素
 * @DateTime: 2021/10/25 20:51
 */
public class KthLargestElementInAnArray215 {

    public static void quickSort(int[] nums, int low, int high) {

        if (low >= high) {
            return;
        }

        // 找到划分点
        int first = low, last = high, pivot = nums[low];
        while(first < last) {
            while(first < last && nums[last] <= pivot) {
                last--;
            }
            nums[first] = nums[last];
            while(first < last && nums[first] >= pivot) {
                first++;
            }
            nums[last] = nums[first];
        }
        nums[first] = pivot;
        quickSort(nums, low, first - 1);
        quickSort(nums, first + 1, high);
    }

    /**
     * @solution 先对数组进行快排，然后再返回第k个数字即可
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[k-1];
    }


    /**
     * @solution 利用快速排序的思想，直接返回第k大的元素即可，不需要再继续排下去
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        int low = 0, high = len-1;
        k--;
        int pos;
        do {
            pos = partition(nums, low, high);

            if (pos < k) {
                low = pos + 1;
            }else if (pos > k){
                high = pos - 1;
            }else{
                break;
            }

        }while (true);


        return nums[pos];
    }

    public int partition(int[] nums, int low, int high) {
        int pivot = nums[low];

        while (low < high) {
            while (low < high && nums[high] <= pivot) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] >= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;

        KthLargestElementInAnArray215 obj = new KthLargestElementInAnArray215();

        System.out.println(obj.findKthLargest1(nums, k));
    }
}

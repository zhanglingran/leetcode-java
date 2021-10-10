package twopointeralgorithm;

/**
 * @author ZhangLingRan
 */
public class TwoSumInputArrayIsSorted167 {


    /**
     * 非递减序列排列
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {

        int len = numbers.length;
        int start = 0, end = len - 1;

        // 进行内存优化一波
        while (start < end) {

            // 可借此降低循环内的访存次数，进一步提高运行时间
            int sum = numbers[start] + numbers[end];

            if (sum > target) {
                end--;
            } else if (sum < target) {
                start++;
            } else {
                //answer[0] = start + 1;
                //answer[1] = end + 1;
                break;
            }
        }

        // 在return的时候创建并返回数组，可有效提高内存容量大小？
        return new int[]{start+1, end+1};
    }

    public static void main(String[] args) {
        TwoSumInputArrayIsSorted167 obj = new TwoSumInputArrayIsSorted167();
        int[] num = {2,7,11,15};
        int[] res = obj.twoSum(num, 9);
        for (int i = 0;i < res.length;i++) {
            System.out.println(res[i]);
        }

    }
}

package sortalgorithm;

import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * @DateTime: 2021/10/25 21:24
 * @TODO 对Map的几种底层实现仔细的研究（包括数据结构）！ 包括lambda表达式的使用和Map的Sort方法
 */
public class TopKFrequentElements347 {

    public int[] topKFrequent(int[] nums, int k) {

        //对map进行排序 使用TreeMap
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key)+1);
            }else {
                map.put(key, 1);
            }
        }


        // 对Map进行排序 需要用LinkedHashMap
        Map<Integer, Integer> sorted = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()
                        .reversed()).forEachOrdered(e -> sorted.put(e.getKey(), e.getValue()));

        int[] res = new int[k];
        int i = 0;
        Set<Integer> keySet = sorted.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (i < k) {
            int ke = iterator.next();
            res[i++] = ke;
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements347 obj = new TopKFrequentElements347();
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] res = obj.topKFrequent(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}

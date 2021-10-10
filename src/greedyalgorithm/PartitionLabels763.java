package greedyalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangLingRan
 */
public class PartitionLabels763 {
    public List<Integer> partitionLabels(String s) {

        List<Integer> result = new ArrayList<>();

        Map<Character, List<Integer>> hashMap = new HashMap<Character, List<Integer>>(s.length());
        for (int i = 0; i < s.length(); i++) {
            List<Integer> tmp = new ArrayList<>();
            char c = s.charAt(i);
            if(hashMap.containsKey(c)){
                tmp = hashMap.get(c);
                tmp.remove(1);
            }else{
                tmp.add(i);
            }
            tmp.add(i);
            hashMap.put(c,tmp);
        }


        List<Integer> pair = hashMap.get(s.charAt(0));
        int start = pair.get(0);
        int end = pair.get(1);

        for (int i = 1; i < s.length(); i++) {
            pair = hashMap.get(s.charAt(i));
            if(pair.get(0) < end) {
                end = Math.max(end, pair.get(1));
            } else {
                result.add(end-start+1);
                end = pair.get(1);
                start = pair.get(0);
            }
        }
        result.add(end - start + 1);
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels763 obj = new PartitionLabels763();
        List<Integer> res = obj.partitionLabels("eccbbbbdec");
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}

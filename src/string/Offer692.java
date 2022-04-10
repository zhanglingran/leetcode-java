import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/8 0:15
 */
public class Offer692 {

    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<Word1> queue = new PriorityQueue<>(new Comparator<Word1>() {

            @Override
            public int compare(Word1 o1, Word1 o2) {

                if (o1.count == o2.count) {
                    return o1.word.compareTo(o2.word);
                }
                return o2.count - o1.count;
            }
        });


        Map<String, Word1> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                Word1 w = map.get(word);
                w.count += 1;
                map.put(word, w);
            } else {
                map.put(word, new Word1(1, word));
            }
        }

        for (Map.Entry<String, Word1> entry : map.entrySet()) {
            queue.add(entry.getValue());
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(queue.poll().word);
        }

        return res;
    }

}


class Word1 {
    int count;
    String word;

    public Word1() {

    }

    public Word1(int count, String word) {
        this.count = count;
        this.word = word;
    }
}

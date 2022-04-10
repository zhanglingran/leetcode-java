import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/6 18:56
 */
public class P1 {

    // 1、定义一个对象，其中包含了词频，在标题中出现的次数、标题中出现的顺序、正文中的顺序
    public static void main(String[] args) {

        // 2、定义优先级队列，并定义优先级顺序
        PriorityQueue<Word> queue = new PriorityQueue<>(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.wCount == o2.wCount) {
                    if (o1.wCountInTitle == o2.wCountInTitle) {
                        if (o1.posInTitle == o2.posInTitle) {
                            return o1.posInContent - o2.posInContent;
                        }
                        return o1.posInTitle - o2.posInTitle;
                    }
                    return o2.wCountInTitle - o1.wCountInTitle;
                }
                return o2.wCount - o1.wCount;
            }
        });

        int topN, M;
        Scanner scan = new Scanner(System.in);
        topN = scan.nextInt();
        M = scan.nextInt();

        scan.nextLine();
        Map<String, Word> map = new HashMap<>();

        for (int i = 0; i < 2*M; i++) {
            String input = scan.nextLine();
            String[] word = input.split(" ");
            for (int j = 0; j < word.length; j++) {
                int rate = 1;
                if (i % 2 == 0) {
                    rate = 3;
                }
                if (map.containsKey(word[j])) {
                    Word tmp = map.get(word[j]);
                    tmp.wCount += rate;
                    tmp.wCountInTitle += (rate == 3 ? 1 : 0);
                    map.put(word[j], tmp);
                } else {
                    Word tmp = new Word();
                    tmp.word = word[j];
                    tmp.wCount = rate;
                    tmp.wCountInTitle = (rate == 3 ? 1 : 0);
                    tmp.posInTitle = (rate == 3 ? j : Integer.MAX_VALUE);
                    tmp.posInContent = (rate == 1 ? j : Integer.MAX_VALUE);
                    map.put(word[j], tmp);
                }
            }
        }


        // 循环处理 map 将其放入优先级队列？
        for (Map.Entry<String, Word> entry : map.entrySet()) {
            queue.add(entry.getValue());
        }

        // 获取前 topN 个即可
        for (int i = 0; i < topN-1; i++) {
            System.out.printf("%s ", queue.poll().word);
        }
        System.out.println(queue.peek().word);
    }
}

class Word {
    String word;
    int wCount;
    int wCountInTitle;
    int posInTitle;
    int posInContent;
}


package top_100;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2021/11/27 14:53
 */
public class P146_LRUCache {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input capacity of LRU:");
        int capacity = scanner.nextInt();
        LRUCache obj = new LRUCache(capacity);

        while (true) {
            System.out.println("choose the option:(1)get, (2)put, (3)quit");
            int option = scanner.nextInt();
            int key, value;
            switch (option) {
                case 1:
                    key = scanner.nextInt();
                    System.out.println("the key " + key + "'s value is : " + obj.get(key));
                    obj.showLRU();
                    break;
                case 2:
                    key = scanner.nextInt();
                    value = scanner.nextInt();
                    obj.put(key, value);
                    obj.showLRU();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

}


class LRUCache {

    private DoubleLinkedList dList;
    private Map<Integer, DoubleLinkedList> hash;
    private int capacity;
    private DoubleLinkedList head;
    private DoubleLinkedList tail;

    /**
     * 创建一个LRUCatch需要一个哈希与链表的组合
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.hash = new HashMap<>(capacity);
        this.dList = new DoubleLinkedList();
        this.capacity = capacity;

        // 初始化的时候，在双向链表的两端加上头尾节点，防止在后边的function中判断边界
        this.head = new DoubleLinkedList();
        this.tail = new DoubleLinkedList();
        this.tail.prev = this.head;
        this.head.next = this.tail;
    }

    public int get(int key) {

        // 如果key不存在于hash中，直接返回-1
        if (!this.hash.containsKey(key)) {
            return -1;
        }

        // 如果key存在于hash中，那么将该key对应的节点移动到双向链表的tail，并返回对应的值
        DoubleLinkedList node = this.hash.get(key);

        // 将node节点在链表中摘出来
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // 添加到tail前边
        this.tail.prev.next = node;
        node.prev = this.tail.prev;
        this.tail.prev = node;
        node.next = tail;

        return node.data.getValue();
    }

    public void put(int key, int value) {
        // 1. 判断key是不是已经存在于hash中了！，不存在的话走下边！[需要注意的是：如果存在的话，第二次重写元素需要将该元素的移动到tail]
        if (get(key) != -1) {
            DoubleLinkedList node = this.hash.get(key);
            node.data = new Pair<>(key, value);
            return;
        }
        // 2. 判断是不是满了（这里用小于而不是小于等于 是因为：当size=capacity的时候，hash已经满了）
        if (this.hash.size() < capacity) {
            DoubleLinkedList node = new DoubleLinkedList(new Pair(key, value));
            this.hash.put(key, node);

            tail.prev.next = node;
            node.prev = tail.prev;
            tail.prev = node;
            node.next = tail;
        } else {
        // 2. 如果已经满了，那么需要删除最不常用的一个元素，并再次添加一个新的元素
            // (1) 删除 head 后的一个元素;
            DoubleLinkedList node = this.head.next;
            this.hash.remove(node.data.getKey());
            node.prev.next = node.next;
            node.next.prev = node.prev;
            // (2) 再次调用put就行了呀！
            put(key, value);
        }
    }

    public void showLRU() {
        DoubleLinkedList.showDoubleLinkedList(this.head);
    }
}

class DoubleLinkedList {

    /**
     * 节点的数据
     */
    public Pair<Integer, Integer> data;

    /**
     * 指向后边的指针
     */
    public DoubleLinkedList next;

    /**
     * 指向前边的指针
     */
    public DoubleLinkedList prev;

    /**
     * 构造方法，新建一个节点并将前后指针置空
     *
     * @param data
     */
    public DoubleLinkedList(Pair data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public DoubleLinkedList() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    public static void showDoubleLinkedList(DoubleLinkedList head) {
        DoubleLinkedList pointer = head.next;
        while (pointer != null && pointer.data != null) {
            System.out.println("[" + pointer.data.getKey() + "," + pointer.data.getValue() + "]");
            pointer = pointer.next;
        }
    }
}
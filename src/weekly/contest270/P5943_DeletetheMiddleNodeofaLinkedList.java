package weekly.contest270;

import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
 * @DateTime: 2021/12/5 11:01
 */
public class P5943_DeletetheMiddleNodeofaLinkedList {

    /**
     * 思路：
     *      1. 找到链表的长度 n
     *      2. 遍历 n/2 得到节点并删除
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }

        if (n == 1) {
            return null;
        }

        n = n >> 1;
        p = head;
        while (--n > 0) {
            p = p.next;
        }

        p.next = p.next.next;

        return head;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
package jzoffer;

/**
 * @Author: ZhangLingRan
 * @Description: 删除链表元素
 * @DateTime: 2022/4/2 11:37
 */
public class offer18 {

    /**
     * 只能通过找到链表值为 val 的节点并将其删除
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        if (head.val == val) {
            return head.next;
        }

        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
        return head;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

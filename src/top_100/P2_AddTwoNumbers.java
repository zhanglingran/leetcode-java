package top_100;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2021/11/15 19:52
 */
public class P2_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(-1, null);
        ListNode cur = head;
        ListNode p;

        int carry = 0;
        do {

            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            p = new ListNode((carry + val1 + val2) % 10);

            carry = (carry + val1 + val2) / 10;

            cur.next = p;
            cur = p;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

        } while (l1 != null || l2 != null);

        if (carry != 0) {
            p = new ListNode(carry);
            cur.next = p;
        }

        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

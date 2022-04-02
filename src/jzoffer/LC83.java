package jzoffer;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/2 12:00
 */
public class LC83 {

    public ListNode deleteDuplicates(ListNode head) {

        ListNode left = head;
        ListNode right = head;

        while (right != null) {
            if (left == right) {
                right = right.next;
            } else {
                if (left.val == right.val) {
                    left.next = right.next;
                    right = right.next;
                } else {
                    left = right;
                }
            }
        }

        return head;
    }
}

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1() {}
    ListNode1(int val) { this.val = val; }
    ListNode1(int val, ListNode1 next) { this.val = val; this.next = next; }
}



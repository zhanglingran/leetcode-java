package twopointeralgorithm;

/**
 * 给定一个链表，如果有环路，找出环路的开始点。
 *
 * @author ZhangLingRan
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * @author ZhangLingRan
 */
public class LinkedListCycleII142 {

    public ListNode detectCycle(ListNode head) {

        ListNode fast = head, slow = head;

        // 如果fast走到头，说明没环，那么返回NULL
        do{
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }while (fast != slow);

        //如果有环，那么找到第一次相遇的交点，slow从交点往后走、fast从head往后走，直到相遇即为交点
        fast = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}



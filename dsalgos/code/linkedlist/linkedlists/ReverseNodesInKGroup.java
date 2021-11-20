package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 23-Jul-2021
 */

import java.util.Deque;
import java.util.LinkedList;

public class ReverseNodesInKGroup {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        Deque<ListNode> queue = new LinkedList<>();
        ListNode prev = null, start = null, temp;

        while(head != null) {

            while (head != null && queue.size() < k) {
                queue.addLast(head);
                head = head.next;
            }

            if (start == null)
                start = queue.peekLast();

            if (queue.size() == k) {
                temp = queue.pollLast();
                if (prev != null)
                    prev.next = temp;
                while(temp != null && !queue.isEmpty()) {
                    temp.next = queue.pollLast();
                    temp = temp.next;
                }
                if (temp != null)
                    temp.next = head;
                prev = temp;
            }
        }

        while(prev != null && !queue.isEmpty()) {
            prev.next = queue.pollFirst();
            prev = prev.next;
        }

        return start;
    }
}

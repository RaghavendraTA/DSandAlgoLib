package org.buildwithraghu.lists;

import org.buildwithraghu.utils.ListNode;

public class LinkedListLoop {

    // https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // Find length of the loop in linkedlist that has cycle
    public int lengthOfTheCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean loopExists = false;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                loopExists = true;
                break;
            }
        }
        if (loopExists) {
            int c = 0;
            fast = fast.next;
            while(fast != slow) {
                fast = fast.next;
                c++;
            }
            return c;
        }
        return -1;
    }
}

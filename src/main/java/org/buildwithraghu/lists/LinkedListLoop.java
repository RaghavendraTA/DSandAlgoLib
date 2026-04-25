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

    // https://leetcode.com/problems/happy-number/
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        while (true) {
            slow = getNextNum(slow);
            fast = getNextNum(getNextNum(fast));
            if (fast == 1)
                return true;
            else if (fast == slow)
                return false;
        }
    }

    int getNextNum(int x) {
        int next_num = 0, digit;
        while (x > 0) {
            digit = x % 10;
            x = x / 10;
            next_num += digit * digit;
        }
        return next_num;
    }
}

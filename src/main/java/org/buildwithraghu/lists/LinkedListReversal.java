package org.buildwithraghu.lists;

import org.buildwithraghu.utils.ListNode;

public class LinkedListReversal {

    // https://leetcode.com/problems/reverse-linked-list
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head, cur = head.next, next = head.next.next;
        while(cur != null) {
            cur.next = prev;
            prev = cur;
            cur = next;
            if (next != null)
                next = next.next;
        }
        head.next = null;
        return prev;
    }
}

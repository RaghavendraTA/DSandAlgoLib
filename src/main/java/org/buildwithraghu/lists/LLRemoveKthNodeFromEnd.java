package org.buildwithraghu.lists;

import org.buildwithraghu.utils.ListNode;

public class LLRemoveKthNodeFromEnd {

    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = dummy;
        ListNode temp = dummy;

        for(int i = 0; i < n && node != null; i++) {
            node = node.next;
        }

        while(node != null && node.next != null) {
            temp = temp.next;
            node = node.next;
        }

        if (temp.next != null)
            temp.next = temp.next.next;

        return dummy.next;
    }
}

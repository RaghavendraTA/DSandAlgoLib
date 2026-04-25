package org.buildwithraghu.lists;

import org.buildwithraghu.utils.ListNode;

public class HasCycle {

    private static ListNode root = null;

    private static void insert(int val) {
        if (root == null)
            root = new ListNode(val);
        ListNode temp = root;
        while(temp.next != null)
            temp = temp.next;
        temp.next = new ListNode(val);
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}

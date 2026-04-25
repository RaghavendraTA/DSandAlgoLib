package org.buildwithraghu.linkedin;


public class RotateList_61 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        int n = 0;
        while(temp != null) {
            n++;
            temp = temp.next;
        }
        k = k % n;
        if (k == 0)
            return head;
        temp = head;
        for(int t = n - k - 1; t > 0; t--) {
            temp = temp.next;
        }
        ListNode tail = temp.next;
        temp.next = null;
        temp = tail;
        while(tail != null && tail.next != null)
            tail = tail.next;
        if (tail != null)
            tail.next = head;
        return temp;
    }
}

package org.buildwithraghu.lists;

import org.buildwithraghu.utils.ListNode;

public class LinkedListIntersection {

    // https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode tempA = headA;
        ListNode tempB = headB;
        while(tempA != tempB) {
            tempA = tempA != null ? tempA.next : headB;
            tempB = tempB != null ? tempB.next : headA;
        }
        return tempA;
    }
}

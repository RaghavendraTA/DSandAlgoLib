package org.buildwithraghu.revision;

import org.buildwithraghu.utils.ListNode;

public class LinkedListIntersection_160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tempA = headA;
        ListNode tempB = headB;
        while(tempA != tempB) {
            tempA = tempA != null ? tempA.next : headB;
            tempB = tempB != null ? tempB.next : headA;
        }
        return tempA;
    }
}

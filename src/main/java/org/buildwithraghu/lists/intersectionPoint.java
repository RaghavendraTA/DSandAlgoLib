package org.buildwithraghu.lists;

import org.buildwithraghu.utilities.Node;

public class intersectionPoint {

    Node intersectPoint(Node headA, Node headB) {
        if (headA == null || headB == null) return null;

        Node tempA = headA;
        Node tempB = headB;
        while(tempA != tempB) {
            tempA = tempA != null ? tempA.next : headB;
            tempB = tempB != null ? tempB.next : headA;
        }
        return tempA;
    }

}

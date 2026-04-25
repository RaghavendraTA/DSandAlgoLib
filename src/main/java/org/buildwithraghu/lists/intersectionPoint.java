package linkedlist.linkedlists;

import arrays.cache.Node;

public class intersectionPoint {

    int intersectPoint(Node head1, Node head2) {

        Node temp1 = head1;
        Node temp2 = head2;

        while (temp1 != null || temp2 != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
            if (temp1 == temp2) {
                return temp1.value;
            }
            if (temp1.next == null) {
                temp1 = head2;
            }
            if (temp2.next == null) {
                temp2 = head1;
            }
        }
        return -1;
    }

}

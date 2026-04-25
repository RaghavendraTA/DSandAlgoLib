package linkedlist.linkedlists;

import arrays.cache.Node;

public class MiddleElement {

    int getMiddle(Node head)
    {
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow != null ? slow.value : -1;
    }
}

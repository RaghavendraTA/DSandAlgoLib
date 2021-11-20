package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.HashMap;
import java.util.Map;

class Node {
    public int value;
    public Node random;
    public Node next;
    Node (int value) {
        this.value = value;
        this.next = null;
        this.random = null;
    }
}

public class CloneLinkedListUsingRandomPointer {

    static Node clone(Node head) {

        Node x, y;
        Map<Node, Node> map = new HashMap<>();

        x = head;
        while(x != null) {
            y = new Node(x.value);
            map.put(x, y);
            x = x.next;
        }

        x = head;
        while(x != null) {
            y = map.get(x);
            y.next = map.get(x.next);
            y.random = map.get(x.random);
            x = x.next;
        }

        return map.get(head);
    }
}

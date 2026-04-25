package linkedlist.linkedlists;

import arrays.cache.Node;

public class MergeTwoSortedList {

    Node sortedMerge(Node head1, Node head2) {

        Node root = null;
        Node node = null;

        while(head1 != null && head2 != null) {
            Node temp = null;
            if (head1.value <= head2.value) {
                temp = head1;
                head1 = head1.next;
            } else {
                temp = head2;
                head2 = head2.next;
            }
            if (node == null) {
                node = temp;
                root = node;
            } else {
                node.next = temp;
                node = node.next;
                node.next = null;
            }
        }

        if (head1 != null) {
            if (node == null)
                root = head1;
            else
                node.next = head1;
        }
        else if (head2 != null) {
            if (node == null)
                root = head2;
            else
                node.next = head2;
        }

        return root;
    }
}

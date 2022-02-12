package linkedlist.linkedlists;

import arrays.cache.Node;

public class DeleteNodeWithoutHead {

    // This can only work if the "node" is not a Tail
    public static void deleteNodeWithoutHead(Node node) {
        while(node.next.next != null) {
            node.value = node.next.value;
            node = node.next;
        }
        node.value = node.next.value;
        node.next = null;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = null;
        Node temp = root;
        for(int i = 2; i <= 5; i++) {
            temp.next = new Node(i);
            temp.next.next = null;
            temp = temp.next;
        }
        temp = root;
        while(temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
        temp = root.next;
        deleteNodeWithoutHead(temp);
        temp = root;
        while(temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }
}

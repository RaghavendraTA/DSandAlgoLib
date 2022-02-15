package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 22-Jun-2021
 */

import arrays.cache.Node;
import linkedlist.node.ListNode;

import java.util.Stack;

public class ReverseLinkedList {

    public static ListNode<Integer> root = null;

    public static ListNode<Integer> reverseRecursion(final ListNode<Integer> node) {

        if (!node.hasNext()) {
            root = node;
            return node;
        }
        reverseRecursion(node.getNext()).setNext(node);
        return node;
    }

    public static ListNode<Integer> reverseUsingRecursion(final ListNode<Integer> node) {

        if (node == null || !node.hasNext()) {
            return node;
        }
        reverseRecursion(node).setNext(null);
        return root;
    }

    public static ListNode<Integer> reverseUsingStack(ListNode<Integer> node) {

        if (node == null || !node.hasNext()) {
            return node;
        }

        Stack<ListNode<Integer>> stack = new Stack<>();
        stack.push(null);
        while (node != null) {
            stack.push(node);
            node = node.getNext();
        }

        ListNode<Integer> root = stack.peek(), temp = stack.pop();
        while (!stack.isEmpty()) {
            temp.setNext(stack.pop());
            temp = temp.getNext();
        }

        return root;
    }

    public static ListNode<Integer> reverseWithoutAdditionalSpace(final ListNode<Integer> node) {

        if (node == null || !node.hasNext()) {
            return node;
        }

        ListNode<Integer> prev = node, current = node.getNext(), temp = current.getNext();
        prev.setNext(null);
        do {
            current.setNext(prev);
            prev = current;
            current = temp;
            temp = temp != null ? temp.getNext() : null;
        } while (current != null);

        return prev;
    }

    // Working solution
    public static Node reverseList(Node node) {

        if (node == null || node.next == null)
            return node;

        Node prev = node;
        Node cur = node.next;
        prev.next = null;

        while(cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

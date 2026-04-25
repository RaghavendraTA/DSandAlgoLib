package org.buildwithraghu.lists;

import java.util.Stack;

import org.buildwithraghu.utilities.LLNode;
import org.buildwithraghu.utils.ListNode;

public class ReverseLinkedList {

    public static LLNode<Integer> root = null;

    public static LLNode<Integer> reverseRecursion(final LLNode<Integer> node) {

        if (!node.hasNext()) {
            root = node;
            return node;
        }
        reverseRecursion(node.getNext()).setNext(node);
        return node;
    }

    public static LLNode<Integer> reverseUsingRecursion(final LLNode<Integer> node) {

        if (node == null || !node.hasNext()) {
            return node;
        }
        reverseRecursion(node).setNext(null);
        return root;
    }

    public static LLNode<Integer> reverseUsingStack(LLNode<Integer> node) {

        if (node == null || !node.hasNext()) {
            return node;
        }

        Stack<LLNode<Integer>> stack = new Stack<>();
        stack.push(null);
        while (node != null) {
            stack.push(node);
            node = node.getNext();
        }

        LLNode<Integer> root = stack.peek(), temp = stack.pop();
        while (!stack.isEmpty()) {
            temp.setNext(stack.pop());
            temp = temp.getNext();
        }

        return root;
    }

    public static LLNode<Integer> reverseWithoutAdditionalSpace(final LLNode<Integer> node) {

        if (node == null || !node.hasNext()) {
            return node;
        }

        LLNode<Integer> prev = node, current = node.getNext(), temp = current.getNext();
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
    public static ListNode reverseList(ListNode node) {

        if (node == null || node.next == null)
            return node;

        ListNode prev = node;
        ListNode cur = node.next;
        prev.next = null;

        while(cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 26-Jun-2021
 */

import linkedlist.node.ListNode;

import java.util.Stack;

public class FindIntersect {

    public static boolean isIntersectionFound = false;

    public static void intersect(Stack<ListNode<Integer>> stack, ListNode<Integer> node) {
        if (node == null) {
            return;
        }
        intersect(stack, node.getNext());
        if (!stack.isEmpty() && node.equals(stack.peek())) {
            stack.pop();
        } else if (!stack.isEmpty() && !isIntersectionFound) {
            System.out.println(node.getValue() + ", " + stack.peek() + " are connected ");
            isIntersectionFound = true;
        }
    }

    public static void intersectPoint(SingleLinkedList<Integer> list1, SingleLinkedList<Integer> list2) {
        Stack<ListNode<Integer>> stack = new Stack<>();
        ListNode<Integer> node = list1.getNode();
        while(node != null) {
            stack.push(node);
            node = node.getNext();
        }
        intersect(stack, list2.getNode());
    }

    public static void middle(SingleLinkedList<Integer> list1) {
        // Using two pointer

    }

    public static void main(String[] args) {

        SingleLinkedList<Integer> list1 = new SingleLinkedList<>();
        SingleLinkedList<Integer> list2 = new SingleLinkedList<>();
        SingleLinkedList<Integer> mid = new SingleLinkedList<>();

        list1.insertAllAtEnd(1, 4, 5);
        list2.insertAllAtEnd(2, 3);
        mid.insertAllAtEnd(6, 7, 8);

        ListNode<Integer> temp = list1.getNode();
        while(temp.hasNext()) temp = temp.getNext();
        temp.setNext(mid.getNode());

        temp = list2.getNode();
        while(temp.hasNext()) temp = temp.getNext();
        temp.setNext(mid.getNode());

        intersectPoint(list1, list2);
    }

}

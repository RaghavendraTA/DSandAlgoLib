package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 22-Jun-2021
 */

import linkedlist.node.ListNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ReverseLinkedListTest {

    @AfterEach
    public void printNewLine() {
        System.out.println();
    }

    @Test
    public void testLLReverse() {

        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.insertAllAtEnd(1, 2, 3, 4, 5, 6);
        ListNode<Integer> node = ReverseLinkedList.reverseUsingRecursion(ll.getNode());
        while(node != null) {
            System.out.print(node.getValue() + ", ");
            node = node.getNext();
        }
    }

    @Test
    public void testLLReverseUsingStack() {

        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.insertAllAtEnd(1, 2, 3, 4);
        ListNode<Integer> node = ReverseLinkedList.reverseUsingStack(ll.getNode());
        while(node != null) {
            System.out.print(node.getValue() + ", ");
            node = node.getNext();
        }
    }

    @Test
    public void testLLReverseWithExtraSpace() {

        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.insertAllAtEnd(1, 2, 3, 4, 5, 6, 7);
        ListNode<Integer> node = ReverseLinkedList.reverseWithoutAdditionalSpace(ll.getNode());
        while(node != null) {
            System.out.print(node.getValue() + ", ");
            node = node.getNext();
        }
    }

}

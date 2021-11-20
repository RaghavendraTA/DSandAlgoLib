package linkedlist.linkedlists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LLTest {

    @Test
    public void testLLAtFront() {
        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.insertAtFront(1);
        ll.insertAtFront(2);
        ll.insertAtFront(3);
        ll.insertAtFront(4);
        Assertions.assertEquals("[4, 3, 2, 1]", ll.toString());
        ll.popAtFront();
        Assertions.assertEquals("[3, 2, 1]", ll.toString());
        ll.popAtEnd();
        Assertions.assertEquals("[3, 2]", ll.toString());
    }

    @Test
    public void testLLAtLast() {
        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.insertAllAtEnd(1, 2, 3, 4);
        Assertions.assertEquals("[1, 2, 3, 4]", ll.toString());
        ll.popAtEnd();
        Assertions.assertEquals("[1, 2, 3]", ll.toString());
        while(!ll.isEmpty()) {
            ll.popAtEnd();
        }
        Assertions.assertEquals("[]", ll.toString());
        ll.insertAllAtEnd(1,2);
        Assertions.assertEquals("[1, 2]", ll.toString());
    }

    @Test
    public void testDLLAtFront() {
        DoubleLinkedList<Integer> dll = new DoubleLinkedList<>();
        dll.insertAllAtFront(1, 2, 3, 4);
        Assertions.assertEquals("[4, 3, 2, 1]", dll.toString());
        dll.popAtFront();
        Assertions.assertEquals("[3, 2, 1]", dll.toString());
        while(!dll.isEmpty()) {
            dll.popAtFront();
        }
        Assertions.assertEquals("[]", dll.toString());
    }

    @Test
    public void testDLLAtEnd() {
        DoubleLinkedList<Integer> dll = new DoubleLinkedList<>();
        dll.insertAllAtEnd(1, 2, 3, 4);
        Assertions.assertEquals("[1, 2, 3, 4]", dll.toString());
        dll.popAtEnd();
        Assertions.assertEquals("[1, 2, 3]", dll.toString());
        while(!dll.isEmpty()) {
            dll.popAtEnd();
        }
        Assertions.assertEquals("[]", dll.toString());
    }
}

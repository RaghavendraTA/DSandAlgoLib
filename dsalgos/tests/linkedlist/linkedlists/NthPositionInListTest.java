package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 25-Jun-2021
 */

import org.junit.jupiter.api.Test;

public class NthPositionInListTest {

    @Test
    public void testNthPos() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.insertAllAtEnd(1, 2, 3, 4, 5);
        Integer result = NthPositionInList.nthPositionFromLast(2, list);
        System.out.println(result);
    }

    @Test
    public void testNthPosUsingRecursion() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.insertAllAtEnd(1, 2, 3, 4, 5);
        Integer result = NthPositionInList.nthPositionFromLastUsingRecursion(2, list);
        System.out.println(result);
    }

}

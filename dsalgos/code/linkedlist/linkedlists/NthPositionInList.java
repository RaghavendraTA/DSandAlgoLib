package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 25-Jun-2021
 */

import linkedlist.node.ListNode;

import java.util.HashMap;
import java.util.Map;

public class NthPositionInList {

    public static int nthPositionFromLast(int n, SingleLinkedList<Integer> list) {
        Map<Integer, ListNode<Integer>> map = new HashMap<>();
        int index = 1;
        ListNode<Integer> node = list.getNode();
        while(node != null) {
            map.put(index, node);
            node = node.getNext();
            index++;
        }
        n = index - n;
        return map.containsKey(n) ? map.get(n).getValue() : -1;
    }

    public static ListNode<Integer> tempNode;
    public static int p;

    public static void nthPosFromLastUsingRecursion(int n, ListNode<Integer> node) {
        if (node == null) {
            return;
        }
        nthPosFromLastUsingRecursion(n, node.getNext());
        p++;
        if (p == n) {
            tempNode = node;
        }
    }

    public static int nthPositionFromLastUsingRecursion(int n, SingleLinkedList<Integer> list) {
        tempNode = null;
        p = 0;
        nthPosFromLastUsingRecursion(n, list.getNode());
        return tempNode.getValue();
    }

}

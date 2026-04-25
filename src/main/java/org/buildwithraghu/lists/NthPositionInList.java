package org.buildwithraghu.lists;

import java.util.HashMap;
import java.util.Map;

import org.buildwithraghu.utilities.LLNode;

public class NthPositionInList {

    public static int nthPositionFromLast(int n, SingleLinkedList<Integer> list) {
        Map<Integer, LLNode<Integer>> map = new HashMap<>();
        int index = 1;
        LLNode<Integer> node = list.getNode();
        while(node != null) {
            map.put(index, node);
            node = node.getNext();
            index++;
        }
        n = index - n;
        return map.containsKey(n) ? map.get(n).getValue() : -1;
    }

    public static LLNode<Integer> tempNode;
    public static int p;

    public static void nthPosFromLastUsingRecursion(int n, LLNode<Integer> node) {
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

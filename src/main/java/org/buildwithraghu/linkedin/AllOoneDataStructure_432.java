package org.buildwithraghu.linkedin;

import java.util.*;

public class AllOoneDataStructure_432 {

    static class Node {
        int count;
        Set<String> keys = new HashSet<>();
        Node prev, next;
        Node(int c) {
            count = c;
        }
    }

    Map<String, Node> keyNode = new HashMap<>();
    Node head;
    Node tail;

    public AllOoneDataStructure_432() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (!keyNode.containsKey(key)) {
            Node node;
            if (head.next.count == 1) {
                node = head.next;
            } else {
                node = new Node(1);
                insertAfter(head, node);
            }
            node.keys.add(key);
            keyNode.put(key, node);
        } else {
            Node cur = keyNode.get(key);
            Node next;

            if (cur.next.count == cur.count + 1) {
                next = cur.next;
            } else {
                next = new Node(cur.count + 1);
                insertAfter(cur, next);
            }
            next.keys.add(key);
            keyNode.put(key, next);
            cur.keys.remove(key);
            if (cur.keys.isEmpty())
                remove(cur);
        }
    }

    public void dec(String key) {
        Node cur = keyNode.get(key);
        if (cur.count == 1) {
            keyNode.remove(key);
        } else {
            Node prev;
            if (cur.prev.count == cur.count - 1) {
                prev = cur.prev;
            } else {
                prev = new Node(cur.count - 1);
                insertAfter(cur.prev, prev);
            }
            prev.keys.add(key);
            keyNode.put(key, prev);
        }
        cur.keys.remove(key);
        if (cur.keys.isEmpty())
            remove(cur);
    }

    public String getMaxKey() {
        if (tail.prev == head) return "";
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.keys.iterator().next();
    }

    // private methods
    private void insertAfter(Node prev, Node node) {
        node.next = prev.next;
        node.prev = prev;
        prev.next.prev = node;
        prev.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

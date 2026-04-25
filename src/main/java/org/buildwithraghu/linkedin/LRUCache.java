package org.buildwithraghu.linkedin;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = this.next = null;
        }
    }

    Node head, tail;
    Map<Integer, Node> map;
    int capacity = 0;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insert(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            insert(node);
            return;
        }
        Node node = new Node(key, value);
        if (map.size() >= capacity) {
            map.remove(tail.key);
            remove(tail);
        }
        insert(node);
        map.put(key, node);
    }

    private void remove(Node node) {
        if (node.prev != null)
            node.prev.next = node.next;
        else
            head = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        else
            tail = node.prev;
    }

    private void insert(Node node) {
        node.prev = null;
        node.next = head;
        if (head != null)
            head.prev = node;
        head = node;
        if (tail == null)
            tail = head;
    }
}

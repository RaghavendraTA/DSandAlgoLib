package org.buildwithraghu.linearDS;

import java.util.HashMap;

public class LRUCache {

    static class Node {
        int key, val;
        Node next = null;
        Node prev = null;
        Node(int key, int val) { this.key = key; this.val = val; }
        @Override
        public String toString() {
            return val + "";
        }
    }

    private int capacity = 0;
    private Node root = null, end = null;
    private final HashMap<Integer, Node> keySet;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keySet = new HashMap<>();
    }

    public int get(int key) {
        if (!keySet.containsKey(key)) {
            return -1;
        }
        Node node = keySet.get(key);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (keySet.containsKey(key)) {
            Node node = keySet.get(key);
            node.val = value;
            moveToHead(node);
            return;
        }
        Node newNode = new Node(key, value);
        if (keySet.size() >= capacity) {
            keySet.remove(end.key);
            removeNode(end);
        }
        addToHead(newNode);
        keySet.put(key, newNode);
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        if (node.prev != null) node.prev.next = node.next;
        else root = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else end = node.prev;
    }

    private void addToHead(Node node) {
        node.prev = null;
        node.next = root;
        if (root != null) root.prev = node;
        root = node;
        if (end == null) end = root;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        Node t = root;
        while(t != null) {
            ans.append("(").append(t.key).append(", ").append(t.val).append(") -> ");
            t = t.next;
        }
        ans.delete(ans.length() - 4, ans.length());
        return ans.toString();
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.put(10, 2);
        System.out.println(lru);
        lru.put(11, 3);
        System.out.println(lru);
        lru.put(12, 4);
        System.out.println(lru);
        lru.get(10);
        System.out.println(lru);
        lru.put(13, 5);
        System.out.println(lru);
        lru.get(10);
        System.out.println(lru);
        lru.put(14, 6);
        System.out.println(lru);
    }
}
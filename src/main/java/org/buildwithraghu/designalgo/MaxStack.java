package org.buildwithraghu.designalgo;

import java.util.*;

public class MaxStack {

    static class Node {
        int val;
        Node next, prev;
        Node(int val) { this.val = val; }
    }

    private final Node head, tail;
    private final TreeMap<Integer, List<Node>> map;

    public MaxStack() {
        map = new TreeMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void push(int x) {
        Node node = new Node(x);
        insertAtEnd(node);
        map.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }

    public int pop() {
        Node node = tail.prev;
        removeNode(node);
        List<Node> list = map.get(node.val);
        list.removeLast();
        if (list.isEmpty())
            map.remove(node.val);
        return node.val;
    }

    public int top() {
        return tail.prev.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int key = map.lastKey();
        List<Node> nodes = map.get(key);
        removeNode(nodes.removeLast());
        if (nodes.isEmpty())
            map.remove(key);
        return key;
    }

    private void insertAtEnd(Node node) {
        Node prevNode = tail.prev;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;
    }

    private void removeNode(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}

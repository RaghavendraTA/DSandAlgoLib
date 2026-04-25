package org.buildwithraghu.linkedin;

import java.util.*;

public class MaxStack_716 {

    static class Node {
        int val;
        Node left, right;
        Node(int v) {
            this.val = v;
            this.left = null;
            this.right = null;
        }
    }

    TreeMap<Integer, List<Node>> treeMap;
    Node head, tail;

    public MaxStack_716() {
        treeMap = new TreeMap<>();
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MIN_VALUE);
        head.right = tail;
        tail.left = head;
    }

    // ---- Doubly Linked List helpers ----

    private void add(Node node) {
        node.left = tail.left;
        node.right = tail;
        tail.left.right = node;
        tail.left = node;
    }

    private void remove(Node node) {
        node.left.right = node.right;
        node.right.left = node.left;
    }

    // ---- API ----

    public void push(int x) {
        Node newNode = new Node(x);
        add(newNode);
        treeMap.computeIfAbsent(x, k -> new ArrayList<>()).add(newNode);
    }

    public int pop() {
        Node oldNode = tail.left;
        remove(oldNode);
        List<Node> list = treeMap.get(oldNode.val);
        list.removeLast();
        if (list.isEmpty())
            treeMap.remove(oldNode.val);
        return oldNode.val;
    }

    public int top() {
        return tail.left.val;
    }

    public int peekMax() {
        return treeMap.lastKey();
    }

    public int popMax() {
        int max = treeMap.lastKey();
        List<Node> list = treeMap.get(max);

        Node node = list.removeLast();
        remove(node);

        if (list.isEmpty())
            treeMap.remove(max);
        return node.val;
    }
}
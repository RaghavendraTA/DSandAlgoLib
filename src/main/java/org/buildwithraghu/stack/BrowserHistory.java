package org.buildwithraghu.stack;

// 1472 - https://leetcode.com/problems/design-browser-history
public class BrowserHistory {

    static class Node {
        String url;
        Node next, prev;
        Node(String url) {
            this.url = url;
        }
    }

    Node head, tail, current;

    public BrowserHistory(String homepage) {
        head = new Node("");
        tail = new Node("");
        current = new Node(homepage);
        head.next = current;
        current.prev = head;
        tail.prev = current;
        current.next = tail;
    }

    public void visit(String url) {
        Node node = new Node(url);
        current.next = node;
        node.prev = current;
        node.next = tail;
        tail.prev = node;
        current = node;
    }

    public String back(int steps) {
        for(int i = 0; i < steps; i++) {
            if (current.prev != head) {
                current = current.prev;
            }
        }
        return current.url;
    }

    public String forward(int steps) {
        for(int i = 0; i < steps; i++) {
            if (current.next != tail) {
                current = current.next;
            }
        }
        return current.url;
    }
}

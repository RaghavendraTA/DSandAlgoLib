package org.buildwithraghu.javafeatures.concurrentDS;

public class MyDataStructure {

    static class Node {
        Object value;
        Node next = null, prev = null;
        public Node(Object value) {
            this.value = value;
        }
    }

    private Node root = null;

    public synchronized void add(Object newValue) {
        Node n = new Node(newValue);
        if (root == null) root = n;
        else { root.next = n; n.prev = root; }
        root = n;
        notifyAll();
    }

    public synchronized Object poll() throws InterruptedException {
        if (root == null) return null;
        Node n = root;
        root = root.prev;
        if (root != null)
            root.next = null;
        n.prev = null;
        return n.value;
    }

    public synchronized Object take() throws InterruptedException {
        while (root == null) wait();
        Node n = root;
        root = root.prev;
        if (root != null)
            root.next = null;
        n.prev = null;
        return n.value;
    }
}

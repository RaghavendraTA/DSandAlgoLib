package org.buildwithraghu.lists;

public class MyCircularQueue {

    static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    private Node head, tail;
    private int size;
    private final int capacity;

    public MyCircularQueue(int k) {
        this.size = 0;
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if (size == capacity)
            return false;
        if (head == null) {
            head = new Node(value);
            tail = head;
            head.next = head;
            size++;
            return true;
        }
        tail.next = new Node(value);
        tail = tail.next;
        tail.next = head;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (size == 0)
            return false;
        if (head.next == head) {
            head = null;
            tail = null;
            size = 0;
            return true;
        }
        head = head.next;
        tail.next = head;
        size--;
        return true;
    }

    public int Front() {
        if (size == 0)
            return -1;
        return head.val;
    }

    public int Rear() {
        if (size == 0)
            return -1;
        return tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

package org.buildwithraghu.lists;

import org.buildwithraghu.javafeatures.interfaces.IQueue;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<T extends Comparable<T>> implements IQueue<T> {

    static class Node<T> {
        T val;
        Node<T> next = null;
        Node<T> prev = null;

        public Node(T val) {
            this.val = val;
        }

        public Node(T val, Node<T> next, Node<T> prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> rootStart = null;
    private Node<T> rootEnd = null;

    @Override
    public void push(T E) {
        if (rootStart == null) {
            rootStart = new Node<>(E);
            rootEnd = rootStart;
            return;
        }
        rootEnd.next = new Node<>(E, null, rootEnd);
        rootEnd = rootEnd.next;
    }

    @Override
    public T peek() throws IllegalStateException {
        if (rootEnd == null) {
            throw new IllegalStateException("Queue is Empty");
        }
        return rootEnd.val;
    }

    @Override
    public void pop() throws IllegalStateException {
        if (rootEnd == null) {
            throw new IllegalStateException("Queue is Empty");
        }
        Node<T> t = rootEnd;
        rootEnd = rootEnd.prev;
        rootEnd.next = null;
        t = null;
    }

    @Override
    public boolean isEmpty() {
        return rootStart == null;
    }

    @Override
    public void addFirst(T E) {
        if (rootStart == null) {
            rootStart = new Node<>(E);
            rootEnd = rootStart;
            return;
        }
        rootStart.prev = new Node<>(E, rootStart, null);
        rootStart = rootStart.prev;
    }

    @Override
    public T getFirst() throws IllegalStateException {
        if (rootStart == null) {
            throw new IllegalStateException("Queue is Empty");
        }
        return rootStart.val;
    }

    @Override
    public void pollFirst() throws IllegalStateException {
        if (rootStart == null) {
            throw new IllegalStateException("Queue is Empty");
        }
        Node<T> t = rootStart;
        rootStart = rootStart.next;
        rootEnd.prev = null;
        t = null;
    }

    @Override
    public String toString() {
        Node<T> temp = rootStart;
        List<T> ls = new ArrayList<>();
        while (temp != null) {
            ls.add(temp.val);
            temp = temp.next;
        }
        return ls.toString();
    }
}

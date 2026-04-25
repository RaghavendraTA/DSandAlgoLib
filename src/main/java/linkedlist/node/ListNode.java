package linkedlist.node;

import interfaces.list.LLNode;

public class ListNode<T> implements LLNode<T> {
    public T value;
    public ListNode<T> next;

    public ListNode() {}

    public ListNode(T value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(T value, ListNode<T> next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public ListNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(LLNode<T> next) {
        this.next = (ListNode<T>) next;
    }

    @Override
    public LLNode<T> getPrev() {
        return null;
    }

    @Override
    public void setPrev(LLNode<T> prev) {
        // No-op for singly linked list
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public boolean hasPrev() {
        return false;
    }
}

package linkedlist.node;

import interfaces.list.LLNode;

public class DoubleLinkedNode<T> implements LLNode<T> {
    private T value;
    private DoubleLinkedNode<T> next;
    private DoubleLinkedNode<T> prev;

    public DoubleLinkedNode(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
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
    public DoubleLinkedNode<T> getNext() {
        return next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setNext(LLNode<T> next) {
        this.next = (DoubleLinkedNode<T>) next;
    }

    @Override
    public DoubleLinkedNode<T> getPrev() {
        return prev;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setPrev(LLNode<T> prev) {
        this.prev = (DoubleLinkedNode<T>) prev;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public boolean hasPrev() {
        return prev != null;
    }
}

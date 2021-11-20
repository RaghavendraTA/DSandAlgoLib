package linkedlist.node;

import interfaces.list.LLNode;

public class DoubleLinkedNode<T> implements LLNode<T> {

    T value;
    DoubleLinkedNode<T> next;
    DoubleLinkedNode<T> prev;

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
    public boolean hasNext() {
        return this.next != null;
    }

    public boolean hasPrev() {
        return this.prev != null;
    }

    @Override
    public void setNext(LLNode<T> node) {
        this.next = (DoubleLinkedNode<T>) node;
    }

    public void setPrev(DoubleLinkedNode<T> node) {
        this.prev = node;
    }

    @Override
    public DoubleLinkedNode<T> getNext() {
        return this.next;
    }

    public DoubleLinkedNode<T> getPrev() {
        return this.prev;
    }
}

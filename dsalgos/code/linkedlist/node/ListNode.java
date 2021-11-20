package linkedlist.node;

import interfaces.list.LLNode;

public class ListNode<T> implements LLNode<T> {

    private T value;
    private ListNode<T> next;

    public ListNode(T value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(T value, ListNode<T> node) {
        this.value = value;
        this.next = node;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setNext(LLNode<T> node) {
        this.next = (ListNode<T>) node;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public ListNode<T> getNext() {
        return this.next;
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

package org.buildwithraghu.utilities;

public class ListNode<T> implements LLNode<T> {

    private T value;
    private LLNode<T> next, prev; 

    public ListNode(T value) {
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
    public LLNode<T> getNext() {
        return this.next;
    }

    @Override
    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    @Override
    public LLNode<T> getPrev() {
        return this.prev;
    }

    @Override
    public void setPrev(LLNode<T> prev) {
        this.prev = prev;
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public boolean hasPrev() {
        return this.prev != null;
    }
    
}

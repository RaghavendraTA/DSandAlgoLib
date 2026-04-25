package org.buildwithraghu.lists;

import java.util.Arrays;
import java.util.Iterator;

import org.buildwithraghu.utilities.LLNode;
import org.buildwithraghu.utilities.ListNode;
import org.buildwithraghu.utilities.ListInterface;

public class SingleLinkedList<T> implements ListInterface<T>, Iterable<T> {

    private LLNode<T> root = null;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insertAtFront(T value) {
        LLNode<T> temp = new ListNode<>(value);
        if (root == null) {
            root = temp;
            return;
        }
        root.setNext(temp);
    }

    @SafeVarargs
    @Override
    public final void insertAllAtFront(T... value) {
        Arrays.stream(value).forEach(this::insertAtFront);
    }

    @Override
    public void insertAtEnd(T value) {
        if (root == null) {
            root = new ListNode<>(value);
            return;
        }
        LLNode<T> temp = root;
        while(temp.hasNext()) {
            temp = temp.getNext();
        }
        temp.setNext(new ListNode<>(value));
    }

    @SafeVarargs
    @Override
    public final void insertAllAtEnd(T... values) {
        Arrays.stream(values).forEach(this::insertAtEnd);
    }

    @Override
    public T popAtFront() {
        T value = root.getValue();
        root = root.getNext();
        return value;
    }

    @Override
    public T popAtEnd() {
        T value = root.getValue();
        if (!root.hasNext()) {
            root = null;
            return value;
        }
        LLNode<T> temp = root, prev = null;
        while(temp.hasNext()) {
            prev = temp;
            temp = temp.getNext();
        }
        if (prev != null) {
            prev.setNext(null);
        }
        return temp.getValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public org.buildwithraghu.utilities.LLNode<T> getNode() {
        return this.root;
    }

    @Override
    public T getValue() {
        return root.getValue();
    }

    @Override
    public Iterator<T> iterator() {
        return new LLIterator<>(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LLNode<T> node = root;
        while(node != null) {
            sb.append(node.getValue()).append(" -> ");
            node = node.getNext();
        }
        return sb.toString();
    }
}

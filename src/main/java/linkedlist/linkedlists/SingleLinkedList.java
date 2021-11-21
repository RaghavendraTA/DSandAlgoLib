package linkedlist.linkedlists;

import interfaces.list.ListInterface;
import linkedlist.node.ListNode;

import java.util.Arrays;
import java.util.Iterator;

public class SingleLinkedList<T> implements ListInterface<T>, Iterable<T> {

    private ListNode<T> root = null;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insertAtFront(T value) {
        root = new ListNode<>(value, root);
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
        ListNode<T> temp = root;
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
        ListNode<T> temp = root, prev = null;
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
    public ListNode<T> getNode() {
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
        return toString(this);
    }
}

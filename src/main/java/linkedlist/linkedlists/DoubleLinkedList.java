package linkedlist.linkedlists;

import interfaces.list.ListInterface;
import linkedlist.node.DoubleLinkedNode;

import java.util.Arrays;
import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<T>> implements ListInterface<T>, Iterable<T> {

    private DoubleLinkedNode<T> rootNode = null, lastNode = null;

    @Override
    public DoubleLinkedNode<T> getNode() {
        return this.rootNode;
    }

    @Override
    public T getValue() { return rootNode.getValue(); }

    public T getLastValue() { return lastNode.getValue(); }

    @Override
    public void insertAtEnd(T value) {
        DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(value);
        if (rootNode == null) {
            rootNode = temp;
            lastNode = temp;
            return;
        }
        temp.setPrev(lastNode);
        lastNode.setNext(temp);
        lastNode = temp;
    }

    @SafeVarargs
    @Override
    public final void insertAllAtEnd(T... value) {
        Arrays.stream(value).forEach(this::insertAtEnd);
    }

    @Override
    public void insertAtFront(T value) {
        DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(value);
        if(rootNode == null) {
            rootNode = temp;
            lastNode = temp;
            return;
        }
        temp.setNext(rootNode);
        rootNode.setPrev(temp);
        rootNode = temp;
    }

    @SafeVarargs
    @Override
    public final void insertAllAtFront(T... value) {
        Arrays.stream(value).forEach(this::insertAtFront);
    }

    @Override
    public T popAtEnd() {
        T value = lastNode.getValue();
        if (lastNode.hasPrev()) {
            lastNode = lastNode.getPrev();
            lastNode.setNext(null);
        } else {
            rootNode = null;
            lastNode = null;
        }
        return value;
    }

    @Override
    public T popAtFront() {
        T value = rootNode.getValue();
        if (rootNode.hasNext()) {
            rootNode = rootNode.getNext();
            rootNode.setPrev(null);
        } else {
            rootNode = null;
            lastNode = null;
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return rootNode == null && lastNode == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LLIterator<>(this);
    }

    @Override
    public String toString() {
        return toString(this);
    }

    public void remove(T value) {
        DoubleLinkedNode<T> node = rootNode;
        while(node != null && node.getValue().compareTo(value) != 0) {
            node = node.getNext();
        }
        if (node != null) {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            node.setPrev(null);
            node.setNext(null);
        }
    }
}

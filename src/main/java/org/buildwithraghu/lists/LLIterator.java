package linkedlist.linkedlists;

import interfaces.list.ListInterface;
import linkedlist.node.ListNode;

import java.util.Iterator;

public class LLIterator<T> implements Iterator<T> {

    private ListNode<T> cursor;

    public LLIterator(ListInterface<T> linkedList) {
        this.cursor = (ListNode<T>) linkedList.getNode();
    }

    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    @Override
    public T next() {
        T v = cursor.getValue();
        cursor = cursor.getNext();
        return v;
    }
}

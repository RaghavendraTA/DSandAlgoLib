package linkedlist.linkedlists;

import interfaces.list.LLNode;
import interfaces.list.ListInterface;

import java.util.Iterator;

public class LLIterator<T> implements Iterator<T> {

    private LLNode<T> cursor;

    public LLIterator(ListInterface<T> linkedList) {
        this.cursor = linkedList.getNode();
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

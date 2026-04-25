package org.buildwithraghu.lists;

import java.util.Iterator;

import org.buildwithraghu.utilities.LLNode;
import org.buildwithraghu.utilities.ListInterface;

public class LLIterator<T> implements Iterator<T> {

    private LLNode<T> cursor;

    public LLIterator(ListInterface<T> linkedList) {
        this.cursor = (LLNode<T>) linkedList.getNode();
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

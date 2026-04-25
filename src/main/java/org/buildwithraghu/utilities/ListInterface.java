package org.buildwithraghu.utilities;

public interface ListInterface<T> {
    boolean isEmpty();
    void insertAtFront(T value);
    void insertAtEnd(T value);
    void insertAllAtFront(T... value);
    void insertAllAtEnd(T... value);
    T popAtFront();
    T popAtEnd();
    T getValue();
    LLNode<T> getNode();
}

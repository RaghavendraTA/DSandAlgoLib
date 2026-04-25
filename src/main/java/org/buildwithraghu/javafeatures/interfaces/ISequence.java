package org.buildwithraghu.javafeatures.interfaces;

public interface ISequence<T extends Comparable<T>> {
    void push(T E);

    T peek() throws IllegalStateException;

    void pop() throws IllegalStateException;

    boolean isEmpty();

    default boolean empty() { return isEmpty(); }

    default void addLast(T E) { push(E); }

    default void pollLast() throws IllegalStateException { pop(); }

    default T getLast() throws IllegalStateException { return peek(); }
}
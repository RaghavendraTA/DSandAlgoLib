package org.buildwithraghu.javafeatures.interfaces;

public interface IQueue<T extends Comparable<T>> extends ISequence<T> {
    void addFirst(T E);

    void pollFirst() throws IllegalStateException;

    T getFirst() throws IllegalStateException;
}

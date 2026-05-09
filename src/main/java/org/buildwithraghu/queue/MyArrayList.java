package org.buildwithraghu.queue;

public class MyArrayList<E> {

    private Object[] elementData;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public void add(E e) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return (E) elementData[index];
    }

    private void ensureCapacity() {
        int newSize = elementData.length + (elementData.length >> 1);
        Object[] newArray = new Object[newSize];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    public E remove(int index) {
        // 1. Validation
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // 2. Capture the old value
        @SuppressWarnings("unchecked")
        E oldValue = (E) elementData[index];

        // 3. Calculate how many elements need to be shifted
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            // Copy from (index + 1) to (index)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        // 4. Clear the last reference for the Garbage Collector
        elementData[--size] = null;

        // 5. Return the removed element
        return oldValue;
    }

    public int size() {
        return size;
    }
}

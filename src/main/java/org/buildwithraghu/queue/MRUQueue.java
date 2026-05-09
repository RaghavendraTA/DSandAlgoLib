package org.buildwithraghu.queue;

public class MRUQueue {

    static class MyArrayList<E> {

        private Object[] elementData;
        private int size = 0;

        public MyArrayList(int capacity) {
            this.elementData = new Object[capacity];
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

        public int size() {
            return size;
        }

        public E remove(int index) {
            // 1. Validation
            if (index >= size || index < 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            @SuppressWarnings("unchecked")
            E oldValue = (E) elementData[index];
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                // Copy from (index + 1) to (index)
                System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            }
            elementData[--size] = null;
            return oldValue;
        }
    }

    MyArrayList<Integer> ls;

    public MRUQueue(int n) {
        ls = new MyArrayList<>(n);
        for(int i = 1; i <= n; i++)
            ls.add(i);
    }

    public int fetch(int k) {
        int t = ls.remove(k-1);
        ls.add(t);
        return t;
    }
}

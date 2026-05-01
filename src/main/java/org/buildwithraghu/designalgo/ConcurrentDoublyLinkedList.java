package org.buildwithraghu.designalgo;

import java.util.*;

/**
 * A thread-safe doubly linked list implementing Deque.
 * Uses synchronized methods for thread safety — simple, correct, and easy to explain.
 *
 * All elements must be non-null. Uses sentinel (dummy) nodes for head and tail
 * to simplify edge-case handling (no null checks at boundaries).
 *
 * Iterators are weakly consistent — they reflect state at some point during iteration,
 * never throw ConcurrentModificationException, and may or may not include concurrent changes.
 */
public class ConcurrentDoublyLinkedList<T> implements Deque<T> {

    /**
     * Doubly linked node with prev and next pointers.
     * Private static inner class — encapsulated, no generic shadowing.
     */
    private static class Node<T> {
        T val;
        Node<T> next, prev;

        Node(T val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    // Sentinel (dummy) nodes simplify boundary logic.
    // head -> ... -> tail forms a ring; real elements live between them.
    private final Node<T> head;
    private final Node<T> tail;
    private int size;

    @SuppressWarnings("unchecked")
    public ConcurrentDoublyLinkedList() {
        head = new Node<>((T) new Object()); // dummy head
        tail = new Node<>((T) new Object()); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    // ======================== Core Deque Operations ========================

    @Override
    public synchronized void addFirst(T val) {
        Node<T> node = new Node<>(val);
        Node<T> nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
        size++;
    }

    @Override
    public synchronized void addLast(T val) {
        Node<T> node = new Node<>(val);
        Node<T> prevNode = tail.prev;
        prevNode.next = node;
        node.prev = prevNode;
        tail.prev = node;
        node.next = tail;
        size++;
    }

    @Override
    public synchronized boolean offerFirst(T val) {
        addFirst(val);
        return true;
    }

    @Override
    public synchronized boolean offerLast(T val) {
        addLast(val);
        return true;
    }

    @Override
    public synchronized T removeFirst() {
        if (head.next == tail)
            return null;
        T val = head.next.val;
        removeNode(head.next);
        size--;
        return val;
    }

    @Override
    public synchronized T removeLast() {
        if (head.next == tail)
            return null;
        T val = tail.prev.val;
        removeNode(tail.prev);
        size--;
        return val;
    }

    @Override
    public synchronized T pollFirst() {
        return removeFirst();
    }

    @Override
    public synchronized T pollLast() {
        return removeLast();
    }

    @Override
    public synchronized T getFirst() {
        if (head.next == tail)
            return null;
        return head.next.val;
    }

    @Override
    public synchronized T getLast() {
        if (head.next == tail)
            return null;
        return tail.prev.val;
    }

    @Override
    public synchronized T peekFirst() {
        return getFirst();
    }

    @Override
    public synchronized T peekLast() {
        return getLast();
    }

    // ======================== Stack Operations (LIFO) ========================

    /** Push onto top of stack = add to head. */
    @Override
    public synchronized void push(T val) {
        addFirst(val);
    }

    /** Pop from top of stack = remove from head. */
    @Override
    public synchronized T pop() {
        T val = removeFirst();
        if (val == null)
            throw new NoSuchElementException();
        return val;
    }

    // ======================== Queue Operations ========================

    @Override
    public synchronized boolean offer(T val) {
        return offerLast(val);
    }

    @Override
    public synchronized boolean add(T val) {
        addLast(val);
        return true;
    }

    /** Removes and returns head; throws if empty. */
    @Override
    public synchronized T remove() {
        T val = removeFirst();
        if (val == null)
            throw new NoSuchElementException();
        return val;
    }

    @Override
    public synchronized T poll() {
        return pollFirst();
    }

    /** Returns head; throws if empty. */
    @Override
    public synchronized T element() {
        T val = getFirst();
        if (val == null)
            throw new NoSuchElementException();
        return val;
    }

    @Override
    public synchronized T peek() {
        return peekFirst();
    }

    // ======================== Arbitrary Removal / Search ========================

    @Override
    public synchronized boolean remove(Object o) {
        Node<T> temp = head.next;
        while (temp != tail) {
            if (o == null ? temp.val == null : o.equals(temp.val)) {
                removeNode(temp);
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public synchronized boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public synchronized boolean removeLastOccurrence(Object o) {
        Node<T> temp = tail.prev;
        while (temp != head) {
            if (o == null ? temp.val == null : o.equals(temp.val)) {
                removeNode(temp);
                size--;
                return true;
            }
            temp = temp.prev;
        }
        return false;
    }

    @Override
    public synchronized boolean contains(Object o) {
        Node<T> temp = head.next;
        while (temp != tail) {
            if (o == null ? temp.val == null : o.equals(temp.val))
                return true;
            temp = temp.next;
        }
        return false;
    }

    @Override
    public synchronized boolean containsAll(Collection<?> c) {
        if (c == null)
            return false;
        for (Object item : c) {
            if (item == null)
                return false; // this list doesn't support null
            boolean found = false;
            Node<T> temp = head.next;
            while (temp != tail) {
                if (item.equals(temp.val)) {
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (!found)
                return false;
        }
        return true;
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        if (c == null)
            return false;
        if (c.isEmpty())
            return false;
        Node<T> temp = head.next;
        while (temp != tail) {
            Node<T> next = temp.next;
            if (c.contains(temp.val)) {
                removeNode(temp);
                size--;
            }
            temp = next;
        }
        return true;
    }

    @Override
    public synchronized boolean retainAll(Collection<?> c) {
        if (c == null)
            return false;
        if (c.isEmpty()) {
            clear();
            return true;
        }
        Node<T> temp = head.next;
        while (temp != tail) {
            Node<T> next = temp.next;
            if (!c.contains(temp.val)) {
                removeNode(temp);
                size--;
            }
            temp = next;
        }
        return true;
    }

    @Override
    public synchronized void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // ======================== Bulk Operations ========================

    @Override
    public synchronized boolean addAll(Collection<? extends T> c) {
        if (c == null)
            return false;
        for (T item : c)
            addLast(item);
        return true;
    }

    // ======================== Size / Empty ========================

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized boolean isEmpty() {
        return head.next == tail;
    }

    // ======================== Array Conversion ========================

    @Override
    public synchronized Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> temp = head.next;
        for (int i = 0; i < size; i++) {
            arr[i] = temp.val;
            temp = temp.next;
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            return (T1[]) Arrays.copyOf(a, size);
        Node<T> temp = head.next;
        for (int i = 0; i < size; i++) {
            a[i] = (T1) temp.val;
            temp = temp.next;
        }
        if (size < a.length)
            a[size] = null;
        return a;
    }

    // ======================== Iterators ========================

    @Override
    public synchronized Iterator<T> iterator() {
        return new DLLIterator(head.next);
    }

    @Override
    public synchronized Iterator<T> descendingIterator() {
        return new DLLDescendingIterator(tail.prev);
    }

    /** Forward iterator: head -> tail. Uses sentinel to detect end. */
    private final class DLLIterator implements Iterator<T> {
        private Node<T> current;

        DLLIterator(Node<T> start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T val = current.val;
            current = current.next;
            return val;
        }
    }

    /** Reverse iterator: tail -> head. Uses sentinel to detect end. */
    private final class DLLDescendingIterator implements Iterator<T> {
        private Node<T> current;

        DLLDescendingIterator(Node<T> start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != head;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T val = current.val;
            current = current.prev;
            return val;
        }
    }

    // ======================== Helper ========================

    /**
     * Removes a node from the list. Caller must hold the lock (synchronized).
     * Assumes node is a real node (not head or tail sentinel).
     */
    private void removeNode(Node<T> node) {
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // ======================== Testing ========================
    public static void main(String[] args) {
        ConcurrentDoublyLinkedList<Integer> dll = new ConcurrentDoublyLinkedList<>();

        // Add alternating: 0 (last), 1 (first), 2 (last), 3 (first), 4 (last)
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0)
                dll.addLast(i);
            else
                dll.addFirst(i);
        }

        // Forward: 1 3 0 2 4
        System.out.print("Forward: ");
        for (Integer v : dll) System.out.print(v + " ");
        System.out.println();

        // Reverse: 4 2 0 3 1
        System.out.print("Reverse: ");
        Iterator<Integer> rev = dll.descendingIterator();
        while (rev.hasNext()) System.out.print(rev.next() + " ");
        System.out.println();

        // Stack ops: push/pop = LIFO
        dll.push(10);
        dll.push(20);
        System.out.print("After push(10), push(20) forward: ");
        for (Integer v : dll) System.out.print(v + " ");
        System.out.println();
        System.out.print("Pop order (LIFO): ");
        System.out.print(dll.pop() + " "); // 20
        System.out.println(dll.pop());     // 10

        // Remove element
        dll.remove(Integer.valueOf(0));
        System.out.print("After removing 0: ");
        for (Integer v : dll) System.out.print(v + " ");
        System.out.println();

        // containsAll fix
        ConcurrentDoublyLinkedList<String> dll2 = new ConcurrentDoublyLinkedList<>();
        dll2.add("A");
        dll2.add("B");
        System.out.println("Contains A: " + dll2.contains("A"));
        System.out.println("Contains [A,B]: " + dll2.containsAll(Arrays.asList("A", "B")));
        System.out.println("Contains [A,C]: " + dll2.containsAll(Arrays.asList("A", "C")));

        // clear
        dll.clear();
        System.out.println("After clear, isEmpty: " + dll.isEmpty() + ", size: " + dll.size());

        // element() throws on empty
        try {
            dll.element();
            System.out.println("FAIL: should have thrown");
        } catch (NoSuchElementException e) {
            System.out.println("element() correctly throws on empty");
        }
    }
}

package org.buildwithraghu.designalgo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class KeyType<T> {
    protected T key;
    abstract boolean isEqual(Object o);
    abstract int getHashCode();

    KeyType(T key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        return this.isEqual(o);
    }

    @Override
    public int hashCode() {
        return this.getHashCode();
    }
}

/**
 * LRUCache generic type should implement KeyType, Must override below methods</br>
 * 1. equals()</br>
 * 2. hashcode()</br>
 */
public class LRUCache<U extends KeyType, V> {

    static class Node<U, V> {
        U key;
        V val;
        Node<U, V> next, prev;
        Node(U key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<U, Node<U, V>> cache;
    private final Node<U, V> head;
    private final Node<U, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public synchronized V get(U key) {
        if (!cache.containsKey(key))
            return null;
        Node<U, V> node = cache.get(key);
        remove(node);
        insertToFront(node);
        return node.val;
    }

    public synchronized void put(U key, V value) {
        if (cache.containsKey(key)) {
            Node<U, V> node = cache.get(key);
            node.val = value;
            remove(node);
            insertToFront(node);
        } else {
            if (this.cache.size() == capacity) {
                Node<U, V> temp = tail.prev;
                remove(temp);
                cache.remove(temp.key);
            }
            Node<U, V> node = new Node<>(key, value);
            insertToFront(node);
            cache.put(key, node);
        }
    }

    private void remove(Node<U, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToFront(Node<U, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    // ---------------------- Usage -------------------------------------------
    static class MyInteger<T> extends KeyType<T> {

        MyInteger(T key) {
            super(key);
        }

        @Override
        public int getHashCode() {
            return key.hashCode();
        }

        @Override
        boolean isEqual(Object o) {
            if (o.getClass() != MyInteger.class)
                return false;
            return this.key.equals(((MyInteger<T>)o).key);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        LRUCache<MyInteger<Integer>, Integer> lruCache = new LRUCache<>(3);
        service.submit(() -> {
            try {
                for(int i = 1; i <= 100; i++) {
                    Thread.sleep(100);
                    lruCache.put(new MyInteger<>(i), i * 10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        service.submit(() -> {
            try {
                for(int i = 1; i <= 100; i++) {
                    Thread.sleep(110);
                    System.out.println(lruCache.get(new MyInteger<>(i)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
    }
}

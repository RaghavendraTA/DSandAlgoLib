package org.buildwithraghu.designalgo;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// https://leetcode.com/problems/all-oone-data-structure
class AllOne {

    private static class Node {
        int freq;
        Node next, prev;
        Set<String> keys = ConcurrentHashMap.newKeySet();
        Node(int freq) {
            this.freq = freq;
        }
    }

    private final Node head, tail;
    private final Map<String, Node> cache;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock rLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock wLock = rwLock.writeLock();

    public AllOne() {
        cache = new ConcurrentHashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        wLock.lock();
        try {
            if (!cache.containsKey(key)) {
                Node temp;
                if (head.next != tail && head.next.freq == 1) {
                    temp = head.next;
                } else {
                    temp = new Node(1);
                    insertAfter(head, temp);
                }
                temp.keys.add(key);
                cache.put(key, temp);
                return;
            }
            Node temp, node = cache.get(key);
            node.keys.remove(key);
            if (node.next != tail && node.next.freq == node.freq + 1) {
                temp = node.next;
            } else {
                temp = new Node(node.freq + 1);
                insertAfter(node, temp);
            }
            if (node.keys.isEmpty()) {
                removeNode(node);
            }
            temp.keys.add(key);
            cache.put(key, temp);
        } finally {
            wLock.unlock();
        }
    }

    public void dec(String key) {
        wLock.lock();
        try {
            if (!cache.containsKey(key))
                return;
            Node temp, node = cache.get(key);
            node.keys.remove(key);
            if (node.freq == 1) {
                cache.remove(key);
                if (node.keys.isEmpty()) {
                    removeNode(node);
                }
                return;
            }
            if (node.prev != head && node.prev.freq == node.freq - 1) {
                temp = node.prev;
            } else {
                temp = new Node(node.freq - 1);
                insertAfter(node.prev, temp);
            }
            if (node.keys.isEmpty()) {
                removeNode(node);
            }
            temp.keys.add(key);
            cache.put(key, temp);
        } finally {
            wLock.unlock();
        }
    }

    public String getMaxKey() {
        rLock.lock();
        try {
            if (tail.prev == head)
                return "";
            return tail.prev.keys.iterator().next();
        } finally {
            rLock.unlock();
        }
    }

    public String getMinKey() {
        rLock.lock();
        try {
            if (head.next == tail)
                return "";
            return head.next.keys.iterator().next();
        } finally {
            rLock.lock();
        }
    }

    // private methods
    private void insertAfter(Node node, Node neigh) {
        Node nextNode = node.next;
        node.next = neigh;
        neigh.prev = node;
        neigh.next = nextNode;
        nextNode.prev = neigh;
    }

    private void removeNode(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }
}

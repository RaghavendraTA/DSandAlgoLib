package org.buildwithraghu.google;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class LFUCache_Google {

    static class Node {
		Integer key, value, count;
		Node next, prev;
		Node(Integer k, Integer v) {
			key = k;
			value = v;
			count = 1;
			next = null;
			prev = null;
		}
	}
	
	class DLL {
        Node head, tail;
        int size;

        DLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node last = tail.prev;
                remove(last);
                return last;
            }
            return null;
        }
    }

	private final Map<Integer, Node> cache;
	private final Map<Integer, DLL> bucket;
	private final int capacity;
	private int minFreq = 0;

    public LFUCache_Google(int capacity) {
        this.capacity = capacity;
		this.cache = new ConcurrentHashMap<>();
		this.bucket = new ConcurrentHashMap<>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key))
			return -1;
        Node node = cache.get(key);
        update(node);
        return node.value;
    }
    
    public void put(int key, int value) {
		if (capacity == 0) return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            update(node);
            return;
        }

        // Evict if needed
        if (cache.size() == capacity) {
            DLL minList = bucket.get(minFreq);
            Node toRemove = minList.removeLast();
            cache.remove(toRemove.key);
        }

        // Insert new node
        Node node = new Node(key, value);
        cache.put(key, node);
        minFreq = 1;

        DLL list = bucket.getOrDefault(1, new DLL());
        list.addFirst(node);
        bucket.put(1, list);
    }
	
	private void update(Node node) {
        int freq = node.count;
        DLL list = bucket.get(freq);
        list.remove(node);

        // update minFreq
        if (freq == minFreq && list.size == 0) {
            minFreq++;
        }

        node.count++;

        DLL newList = bucket.getOrDefault(node.count, new DLL());
        newList.addFirst(node);
        bucket.put(node.count, newList);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
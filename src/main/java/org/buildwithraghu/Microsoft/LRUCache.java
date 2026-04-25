package org.buildwithraghu.Microsoft;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// https://leetcode.com/problems/lru-cache/
class LRUCache {
	
	static class Node {
		int val, key;
		Node next, prev;
		Node(int key, int val) {
			this.val = val;
			this.key = key;
			next = null;
			prev = null;
		}
	}
	
	Map<Integer, Node> cache;
	int capacity;
	Node head, tail;

    public LRUCache(int capacity) {
        this.cache = new ConcurrentHashMap<>();
		this.capacity = capacity;
		// Doubly Linked List init
		head = new Node(-1, -1);
		tail = new Node(-1, -1);
		head.next = tail;
		tail.prev = head;
    }
    
    public int get(int key) {
		// if the cache is empty
        // if cache doesn't has key
		if (!cache.containsKey(key)) {
			return -1;
		}
		// if cache has key
		Node node = cache.get(key);
		remove(node);
		insertAtFront(node);
        return node.val;
    }
    
    public void put(int key, int value) {
		Node node;
        // if cache has key
		if (cache.containsKey(key)) {
			node = cache.get(key);
			node.val = value;
			remove(node);
			insertAtFront(node);
			return;
		}
		// if the cache is full
		if (cache.size() == capacity) {
			node = tail.prev;
			remove(node);
			cache.remove(node.key);
		}
		// if cache doesn't has key
		node = new Node(key, value);
		insertAtFront(node);
		cache.put(key, node);
    }
	
	// Doubly-LinkedList functions
	private void remove(Node node) {
		Node prevNode = node.prev;
		Node nextNode = node.next;

		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}
	
	private void insertAtFront(Node node) {
		Node temp = head.next;
		head.next = node;
		node.prev = head;
		temp.prev = node;
		node.next = temp;
	}
}
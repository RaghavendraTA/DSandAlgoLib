package org.buildwithraghu.lists;

import org.buildwithraghu.tree.SimpleBST;

// Need complete Binary Search Tree implementation
class MyHashSet {

    private final int keyRange;
    private final SimpleBST[] buckets;

    public MyHashSet() {
        keyRange = 769;
        buckets = new SimpleBST[keyRange];
        for(int i = 0; i < keyRange; i++) {
            buckets[i] = new SimpleBST();
        }
    }

    private int _hash(int key) {
        return (key % keyRange);
    }

    public void add(int key) {
        int idx = _hash(key);
        buckets[idx].insert(key);
    }

    public void remove(int key) {
        int idx = _hash(key);
        buckets[idx].delete(key);
    }

    public boolean contains(int key) {
        int idx = _hash(key);
        return buckets[idx].search(key);
    }
}
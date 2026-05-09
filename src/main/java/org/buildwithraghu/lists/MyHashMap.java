package org.buildwithraghu.lists;

import java.util.LinkedList;

public class MyHashMap {

    private final int key_space;
    private final Bucket[] buckets;

    public MyHashMap() {
        this.key_space = 2069;
        this.buckets = new Bucket[key_space];
        for(int i = 0; i < key_space; i++) {
            buckets[i] = new Bucket();
        }
    }

    private int _hash(int key) {
        return (key % key_space);
    }

    public void put(int key, int value) {
        int idx = _hash(key);
        buckets[idx].update(key, value);
    }

    public int get(int key) {
        int idx = _hash(key);
        return buckets[idx].get(key);
    }

    public void remove(int key) {
        int idx = _hash(key);
        buckets[idx].delete(key);
    }

    static class Pair {
        int key, val;
        Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class Bucket {
        LinkedList<Pair> list;
        Bucket() {
            list = new LinkedList<>();
        }

        int get(int key) {
            for(Pair p: list) {
                if (p.key == key) {
                    return p.val;
                }
            }
            return -1;
        }

        void delete(int key) {
            for(Pair p: list) {
                if (p.key == key) {
                    list.remove(p);
                    return;
                }
            }
        }

        void update(int key, int val) {
            boolean found = false;
            for(Pair p: list) {
                if (p.key == key) {
                    p.val = val;
                    found = true;
                    break;
                }
            }
            if (!found) {
                list.add(new Pair(key, val));
            }
        }
    }
}

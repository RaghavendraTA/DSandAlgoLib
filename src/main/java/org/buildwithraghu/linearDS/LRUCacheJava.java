package org.buildwithraghu.linearDS;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheJava {

    private int capacity = 0;
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCacheJava(int capacity) {
        this.capacity = capacity;
        // LinkedHashMap with accessOrder = true for LRU behavior
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRUCacheJava.this.capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

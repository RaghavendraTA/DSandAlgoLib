package org.buildwithraghu.lowleveldesign.LRUCache;

public class Demo {

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("Raghu", 1);
        cache.put("Raji", 2);
        cache.put("Aishu", 3);

        System.out.println(cache.get("Raghu")); // re-inserts this node in the bottom again

        cache.put("Dheeraj", 4);

        System.out.println(cache.get("Raji")); // null
    }
}

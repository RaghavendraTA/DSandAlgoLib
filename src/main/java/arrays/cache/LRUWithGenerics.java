package arrays.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LRUWithGenerics {

    private int capacity;
    private LinkedHashMap<Integer,Integer> map;

    public LRUWithGenerics(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(16, 0.75f, true);
    }

    public int get(int key) {
        return this.map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (!this.map.containsKey(key) && this.map.size() == this.capacity) {
            Iterator<Integer> it = this.map.keySet().iterator();
            it.next();
            it.remove();
        }
        this.map.put(key, value);
    }
}

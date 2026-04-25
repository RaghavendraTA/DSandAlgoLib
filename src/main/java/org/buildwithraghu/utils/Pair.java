package org.buildwithraghu.utils;

public class Pair<U extends Comparable<U>, V extends Comparable<V>> implements Comparable<Pair<U, V>> {
    public U key;
    public V value;

    public Pair(U first, V second) {
        this.key = first;
        this.value = second;
    }

    @Override
    public int compareTo(Pair<U, V> p) {
        return this.key == p.key ? this.value.compareTo(p.value) : this.key.compareTo(p.key);
    }

    @Override
    public String toString() {
        return this.key + " -> " + this.value;
    }

    public U getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
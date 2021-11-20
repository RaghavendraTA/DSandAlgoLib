package trees.utils;

public class Pair<U, V> {

    private U key;
    private V value;

    public Pair(U key, V value) {
        this.key = key;
        this.value = value;
    }

    public U getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(U key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
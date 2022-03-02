package trees.utils;

/*
 * created by raghavendra.ta on 27-Jun-2021
 */

public class IntPair {

    private Integer key;
    private Integer value;

    public IntPair(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + "-" + value + ")";
    }
}

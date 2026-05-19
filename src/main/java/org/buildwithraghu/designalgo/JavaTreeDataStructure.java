package org.buildwithraghu.designalgo;

import java.util.TreeMap;

public class JavaTreeDataStructure {

    private static void testTreeMap() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(10, 10);
        map.put(2, 2);
        map.put(7, 7);
        map.put(5, 5);
        System.out.println("lastKey = " + map.lastKey());
        System.out.println("firstKey = " + map.firstKey());
        System.out.println("floor(4) = " + map.floorKey(4)); // lower_bound
        System.out.println("ceil(4) = " + map.ceilingKey(4)); // upper_bound
    }

    public static void main(String[] args) {
        testTreeMap();
    }
}

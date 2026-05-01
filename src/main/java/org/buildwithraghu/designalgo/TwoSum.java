package org.buildwithraghu.designalgo;

import java.util.*;

public class TwoSum {
    Map<Integer, Integer> counter;

    public TwoSum() {
        counter = new HashMap<>();
    }

    public void add(int number) {
        counter.merge(number, 1, Integer::sum);
    }

    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry: counter.entrySet()) {
            int neg = value - entry.getKey();
            if (neg == entry.getKey()) {
                if (entry.getValue() > 1) return true;
            } else {
                if (counter.containsKey(neg)) return true;
            }
        }
        return false;
    }
}
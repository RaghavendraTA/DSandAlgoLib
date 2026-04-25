package org.buildwithraghu.linkedin;

import java.util.*;

public class InsertDeleteGetRandomO1_380 {

    class RandomizedSet {

        private List<Integer> arr;
        private Random random;
        private Map<Integer, Integer>  map;

        public RandomizedSet() {
            arr = new ArrayList<>();
            random = new Random();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val);
            int last = arr.getLast();

            map.put(last, idx);
            arr.set(idx, last);

            arr.removeLast();
            map.remove(val);
            return true;
        }

        public int getRandom() {
            int randomIndex = random.nextInt(arr.size());
            return arr.get(randomIndex);
        }
    }
}

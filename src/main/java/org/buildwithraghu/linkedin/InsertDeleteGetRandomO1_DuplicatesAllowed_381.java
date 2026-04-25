package org.buildwithraghu.linkedin;

import java.util.*;

public class InsertDeleteGetRandomO1_DuplicatesAllowed_381 {

    static class RandomizedCollection {

        private List<Integer> arr;
        private Random random;
        private Map<Integer, List<Integer>> map;

        public RandomizedCollection() {
            arr = new ArrayList<>();
            random = new Random();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            boolean flag = !map.containsKey(val);
            map.computeIfAbsent(val, v -> new ArrayList<>()).add(arr.size());
            arr.add(val);
            return flag;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            int idx = map.get(val).removeFirst();
            int lastIndex = arr.size() - 1;
            int last = arr.getLast();

            if (idx != lastIndex) {
                arr.set(idx, last);

                List<Integer> lastList = map.get(last);
                lastList.remove(Integer.valueOf(lastIndex)); // remove old index
                lastList.add(idx); // add new index
            }

            arr.removeLast();

            if (map.get(val).isEmpty()) {
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            return arr.get(random.nextInt(arr.size()));
        }
    }
}

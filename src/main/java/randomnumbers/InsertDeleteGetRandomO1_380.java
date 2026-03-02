package randomnumbers;

import java.util.*;

public class InsertDeleteGetRandomO1_380 {

    static class RandomizedSet {

        HashMap<Integer, Integer> map;
        List<Integer> list;
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val))
                return false;
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;
            int idx = map.get(val);
            int lastValue = list.get(list.size()-1);
            map.put(lastValue, idx);
            map.remove(val);
            list.set(idx, lastValue);
            list.remove(list.size()-1);
            return true;
        }

        public int getRandom() {
            int index = (int) (Math.random() * list.size());
            return list.get(index);
        }
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(10);
        boolean param_2 = obj.remove(20);
        int param_3 = obj.getRandom();
    }
}

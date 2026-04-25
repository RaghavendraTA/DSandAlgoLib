package org.buildwithraghu.designalgo;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class FirstUniqueNumber {

    class FirstUnique {

        final int FIN = 1000;
        BlockingDeque<Integer> que = new LinkedBlockingDeque<>(FIN);
        ConcurrentHashMap<Integer, Boolean> isUnique = new ConcurrentHashMap<>();

        public FirstUnique(int[] nums) {
            for (int i : nums) {
                add(i);
            }
        }

        public int showFirstUnique() {
            while(!que.isEmpty() && !isUnique.get(que.peek())) {
                que.poll();
            }
            if (!que.isEmpty()) {
                return que.peek();
            }
            return -1;
        }

        public void add(int value) {
            if (isUnique.containsKey(value)) {
                isUnique.put(value, false);
            } else {
                isUnique.put(value, true);
                que.add(value);
            }
        }
    }

    public static void main(String[] args) {

    }
}

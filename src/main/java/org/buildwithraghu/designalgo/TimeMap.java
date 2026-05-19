package org.buildwithraghu.designalgo;

import java.util.*;

// https://leetcode.com/problems/time-based-key-value-store/
class TimeMap {

    static class Node {
        String v;
        int t;
        Node(String v, int t) {
            this.v = v;
            this.t = t;
        }
        @Override
        public String toString() {
            return "[v=" + v + ", t=" + t + "]";
        }
    }

    Map<String, List<Node>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Node(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key))
            return "";
        List<Node> list = map.get(key);
        Node node = binarySearch(list, timestamp);
        return node == null ? "" : node.v;
    }

    private Node binarySearch(List<Node> list, int t) {
        int lo = 0;
        int hi = list.size() - 1;
        Node ans = null;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            Node curr = list.get(mid);
            if (curr.t <= t) {
                ans = curr;
                lo = mid + 1; // try to find closer timestamp
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}

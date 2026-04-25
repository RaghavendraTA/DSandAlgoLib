package org.buildwithraghu.designalgo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/all-oone-data-structure/?envType=problem-list-v2&envId=design
public class AllOne {

    static class Node {
        int freq;
        Node prev, next;
        Set<String> keys = new HashSet<>();
        Node(int freq) {
            this.freq = freq;
        }
    }

    Node head, tail;
    Map<String, Node> map = new HashMap<>();

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {

    }

    public void dec(String key) {

    }

    public String getMaxKey() {
        return "MaxKey";
    }

    public String getMinKey() {
        return "MinKey";
    }
}

package org.buildwithraghu.designalgo;

import java.util.*;
import java.util.concurrent.*;

public class Trie {

    static class Node {
        final char c;
        final Map<Character, Node> children;
        volatile boolean isEnd;
        public Node(char c) {
            this.c = c;
            this.children = new ConcurrentHashMap<>();
            this.isEnd = false;
        }
    }

    private final Node root;

    public Trie() {
        root = new Node('#');
    }

    public void insert(String word) {
        Node temp = root;
        for(char c: word.toCharArray()) {
            temp = temp.children.computeIfAbsent(c, (k) -> new Node(c));
        }
        temp.isEnd = true;
    }

    public boolean search(String word) {
        Node temp = root;
        for(char c: word.toCharArray()) {
            if (!temp.children.containsKey(c))
                return false;
            temp = temp.children.get(c);
        }
        return temp.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node temp = root;
        for(char c: prefix.toCharArray()) {
            if (!temp.children.containsKey(c))
                return false;
            temp = temp.children.get(c);
        }
        return true;
    }
}
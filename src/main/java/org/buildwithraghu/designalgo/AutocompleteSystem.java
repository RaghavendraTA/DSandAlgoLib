package org.buildwithraghu.designalgo;

import org.buildwithraghu.utils.Pair;

import java.util.*;

public class AutocompleteSystem {

    static class Node {
        TreeSet<Pair<String, Integer>> listOfInputs;
        char elem;
        Node[] children;
        Node(char c, String input) {
            this.listOfInputs = new TreeSet<>(Comparator.comparingInt(x -> x.value));
            this.elem = c;
            this.children = new Node[26];
        }
    }

    private final Node root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Node('#', "#");
        for(int i = 0; i < sentences.length; i++) {
            insert(root, 0, sentences[i], times[i]);
        }
    }

    private void insert(Node node, int j, String s, int t) {
        if (j >= s.length())
            return;
        int c = s.charAt(j)-'a';
        Node next = node.children[c];
        if (next == null)
            next = new Node(s.charAt(j), s);
        node.children[c] = next;
        node.listOfInputs.add(new Pair<>(s, t));
        insert(next, j + 1, s, t);
    }

    public List<String> input(char c) {
        return null;
    }
}

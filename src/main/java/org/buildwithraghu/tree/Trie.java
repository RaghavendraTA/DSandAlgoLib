package org.buildwithraghu.tree;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Trie {

    static class Node {
        boolean isEnd = false;
        HashMap<Character, Node> children = new HashMap<>();
    }

    private Node root = new Node();

    public void insert(String s) {
        Node temp = root;
        for(char c: s.toCharArray()) {
            temp.children.putIfAbsent(c, new Node());
            temp = temp.children.get(c);
        }
        temp.isEnd = true;
    }

    public boolean contains(String s) {
        Node temp = root;
        for(char c: s.toCharArray()) {
            if (!temp.children.containsKey(c))
                return false;
            temp = temp.children.get(c);
        }
        return true;
    }

    private void concatenateNext(Node temp, String current) {
        if (temp.children.isEmpty() || temp.isEnd)
            System.out.println(current);
        for(char c: temp.children.keySet()) {
            concatenateNext(temp.children.get(c), current + c);
        }
    }

    public void completePartialSearch(String s) {
        StringBuilder newStr = new StringBuilder();
        Node temp = root;
        for(char c: s.toCharArray()) {
            if (!temp.children.containsKey(c))
                break;
            newStr.append(c);
            temp = temp.children.get(c);
        }
        if (temp.isEnd)
            System.out.println(newStr);
        concatenateNext(temp, newStr.toString());
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Rajendra");
        trie.insert("Raghu");
        trie.insert("Raghaveera");
        trie.insert("RaghuDixit");
        trie.insert("Raghavendra");

        // Not working this is why u need to have end of string flag
        trie.completePartialSearch("Raj");
    }
}

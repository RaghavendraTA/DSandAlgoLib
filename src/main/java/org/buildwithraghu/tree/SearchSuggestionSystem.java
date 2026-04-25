package org.buildwithraghu.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** <a href="https://leetcode.com/problems/search-suggestions-system">SearchSuggestionSystem</a> */
public class SearchSuggestionSystem {

    static class Node {
        public char c;
        public Node[] children = new Node[26];
        public boolean isEnd;
        Node(char c) {
            this.c = c;
            Arrays.fill(children, null);
            isEnd = false;
        }
    }

    static class Trie {
        Node root = new Node('#');

        private void insert_(Node node, String s, int i) {
            char c = s.charAt(i);
            if (node.children[c-'a'] == null)
                node.children[c-'a'] = new Node(c);
            if (i == s.length()-1) {
                node.children[c - 'a'].isEnd = true;
                return;
            }
            insert_(node.children[c-'a'], s, i+1);
        }

        public void insert(String s) {
            insert_(root, s, 0);
        }

        private void collect(List<String> ans, Node node, String prefix) {
            if (ans.size() == 3)
                return;
            if (node.isEnd) ans.add(prefix);
            for(Node child: node.children) {
                if (child != null)
                    collect(ans, child, prefix + child.c);
            }
        }

        public List<String> search(String word) {
            List<String> ans = new ArrayList<>();
            Node node = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c-'a'] == null)
                    return ans;
                node = node.children[c-'a'];
            }
            collect(ans, node, word);
            return ans;
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();

        for(String p: products)
            trie.insert(p);

        List<List<String>> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < searchWord.length(); i++) {
            sb.append(searchWord.charAt(i));
            ans.add(trie.search(sb.toString()));
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] products = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        SearchSuggestionSystem sss = new SearchSuggestionSystem();
        System.out.println(sss.suggestedProducts(products, searchWord));
    }
}

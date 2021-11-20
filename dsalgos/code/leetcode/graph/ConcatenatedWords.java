package leetcode.graph;

/*
 * created by raghavendra.ta on 16-Jul-2021
 */

import trees.utils.Pair;

import java.util.*;

class Trie {

    Map<Character, Trie> children;
    boolean isEndOfString;

    Trie() {
        children = new HashMap<>();
        isEndOfString = false;
    }

    public int search(String str, int idx) {
        if (idx >= str.length())
            return -1;
        char c = str.charAt(idx);
        if (isEndOfString && children.containsKey(c)) {
            return idx + 1;
        }
        if (children.containsKey(c))
            return children.get(c).search(str, idx + 1);
        return -1;
    }

    public boolean trieHasSuffix(Trie root, String str, int idx) {
        int index = idx;
        while(index < str.length()) {
            index = root.search(str, index);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    public void insert(Trie root, List<String> result, String str, int idx) {
        if (idx >= str.length()) {
            isEndOfString = true;
            return;
        }
        if (isEndOfString && trieHasSuffix(root, str, idx)) {
            result.add(str);
            return;
        }
        char c = str.charAt(idx);
        children.putIfAbsent(c, new Trie());
        children.get(c).insert(root, result, str, idx + 1);
    }

    public void printTrie(String root) {
        children.forEach((k, v) -> {
            v.printTrie(root + k);
            System.out.println(root + k);
        });
    }
}

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Trie trie = new Trie();
        List<String> result = new ArrayList<>();
        for(String word: words) {
            trie.insert(trie, result, word, 0);
        }
        System.out.println(result);
        trie.printTrie("");
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        ConcatenatedWords c = new ConcatenatedWords();
        c.findAllConcatenatedWordsInADict(words);

        ConcatenatedWords c2 = new ConcatenatedWords();
        String[] words2 = {"a","b","ab","abc"};
        c2.findAllConcatenatedWordsInADict(words2);
    }
}

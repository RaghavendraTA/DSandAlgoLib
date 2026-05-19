package org.buildwithraghu.designalgo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class TextEditor {

    Deque<Character> left, right;

    private String toText(Deque<Character> q) {
        StringBuilder sb = new StringBuilder();
        Iterator<Character> itr = q.descendingIterator();
        for(int i = 0, j = 0; i < 10 && j < q.size(); i++, j++) {
            sb.insert(0, itr.next());
        }
        return sb.toString();
    }

    public TextEditor() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void addText(String text) {
        for(char c: text.toCharArray())
            left.addLast(c);
    }

    public int deleteText(int k) {
        int i = 0;
        while(i < k && !left.isEmpty()) {
            left.removeLast();
            i++;
        }
        return i;
    }

    public String cursorLeft(int k) {
        int i = 0;
        while(i < k && !left.isEmpty()) {
            right.addFirst(left.removeLast());
            i++;
        }
        return toText(left);
    }

    public String cursorRight(int k) {
        int i = 0;
        while(i < k && !right.isEmpty()) {
            left.addLast(right.removeFirst());
            i++;
        }
        return toText(left);
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("leetcode");
        textEditor.deleteText(4);
        textEditor.addText("practice");
        textEditor.cursorRight(3);
        textEditor.cursorLeft(8);
        textEditor.deleteText(10);
        textEditor.cursorLeft(2);
        textEditor.cursorRight(6);
    }
}

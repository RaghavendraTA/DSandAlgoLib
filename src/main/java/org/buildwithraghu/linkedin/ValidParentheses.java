package org.buildwithraghu.linkedin;

import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        Map<Character, Character> map = Map.of('(', ')', '{', '}', '[', ']');
        for(char c: s.toCharArray()) {
            if (map.containsKey(c)) {
                stk.push(map.get(c));
            } else if (!stk.isEmpty() && stk.peek() == c) {
                stk.pop();
            } else {
                return false;
            }
        }
        return stk.isEmpty();
    }
}

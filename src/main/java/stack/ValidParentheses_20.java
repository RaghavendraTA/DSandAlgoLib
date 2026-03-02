package stack;

import java.util.LinkedList;
import java.util.Map;

public class ValidParentheses_20 {

    public boolean isValid(String s) {
        LinkedList<Character> stk = new LinkedList<>();
        Map<Character, Character> map = Map.of('(', ')', '{', '}', '[', ']');
        for(char c: s.toCharArray()) {
            if (map.containsKey(c)) {
                stk.add(map.get(c));
            } else if (!stk.isEmpty() && stk.peekLast() == c) {
                stk.pollLast();
            } else {
                return false;
            }
        }
        return stk.isEmpty();
    }
}

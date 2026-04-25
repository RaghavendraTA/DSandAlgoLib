package arrays.stack;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CheckExpressionIsBalanced {

    public static Map<Character, Character> chars = new HashMap<>();

    static {
        chars.put('(',')');
        chars.put('{','}');
        chars.put('[',']');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()) {
            if (chars.containsKey(c)) {
                stack.push(chars.get(c));
            } else if (!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

}

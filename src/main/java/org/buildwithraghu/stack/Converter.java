package arrays.stack;


import java.util.*;
import java.util.Stack;

abstract class Converter {

    final static Boolean L_TO_R = true;
    final static Boolean R_TO_L = false;
    final static List<Character> R_TO_L_ASSOCIATION_LIST = Collections.singletonList('^');

    final static Map<List<Character>, Integer> precedenceMap;

    static {
        precedenceMap = new HashMap<>();
        precedenceMap.put(Arrays.asList('+', '-'), 1);
        precedenceMap.put(Arrays.asList('*', '/'), 2);
        precedenceMap.put(Collections.singletonList('^'), 3);
        precedenceMap.put(Arrays.asList('{', '}', '(', ')', '[', ']'), -1);
    }

    protected static boolean association(final Character c) {

        return R_TO_L_ASSOCIATION_LIST.contains(c) ? R_TO_L : L_TO_R;
    }

    protected static boolean isSymbol(final Character c) {

        return !Character.isLetter(c) && c != '(' && c != ')';
    }

    protected static int precedence(final Character c) {

        for(Map.Entry<List<Character>, Integer> entry: precedenceMap.entrySet()) {
            if (entry.getKey().contains(c)) {
                return entry.getValue();
            }
        }
        return 99;
    }

    protected static void validateExpression(final String expression) throws Exception {

        Map<Character, Character> expressionManager = Map.of( '}', '{',  ')', '(', ']', '[');
        Set<Character> closures = expressionManager.keySet();
        Set<Character> openers = new HashSet<>(expressionManager.values());
        Stack<Character> stack = new Stack<>();

        for(char c: expression.toCharArray()) {
            if (openers.contains(c)) {
                stack.push(c);
            } else if (closures.contains(c)) {
                if (stack.peek() != expressionManager.get(c)) {
                    throw new Exception("Invalid expression");
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            throw new Exception("Invalid expression");
        }
    }

}

package arrays.stack;

import java.util.Stack;


public class InfixToPrefix extends Converter {

    private static boolean isLesserPrecedence(final Character left,
                                              final Character right) {

        if (precedence(left) == precedence(right)) {
            return !association(left);
        }
        return precedence(left) < precedence(right);
    }

    static void popTillChar(final char c,
                            final Stack<Character> stack,
                            final StringBuilder prefix) {

        while(!stack.isEmpty() && stack.peek() != c) {
            prefix.append(stack.peek());
            stack.pop();
        }
        if (!stack.isEmpty() && stack.peek() == c) {
            stack.pop();
        }
    }

    protected static boolean isOperator(final Character c) {

        return !Character.isLetter(c) && c != '(' && c != ')';
    }

    public static String convert(final String expression) throws Exception {

        //validateExpression(expression);

        String infix = new StringBuilder(expression).reverse().toString();
        StringBuilder prefix = new StringBuilder();

        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (c == ')') {
                stack.push(c);
            }
            else if (isOperator(c)) {
                while (!stack.isEmpty() && isLesserPrecedence(c, stack.peek())) {
                    prefix.append(stack.peek());
                    stack.pop();
                }
                stack.push(c);
            }
            else if (c == '(') {
                popTillChar(')', stack, prefix);
            }
            else {
                prefix.append(c);
            }
        }

        popTillChar(')', stack, prefix);
        return prefix.reverse().toString();
    }
}

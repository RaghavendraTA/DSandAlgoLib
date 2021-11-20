package arrays.stack;


import java.util.Arrays;
import java.util.Stack;

/**
 * isDigit(c)       => postfix.append(c)
 * '('              => stack.push(c)
 * ')'              => while(stack.isNotEmpty() && stack.top != '(')
 *                          postfix.append(stack.pop())
 *                  => stack.pop()
 *
 * else             => while(stack.isNotEmpty() && prec(c) < prec(stack.top))
 *                          postfix.append(stack.pop())
 *                  => stack.push(c)
 */

public class InfixToPostfix extends Converter {

    public static int precedence(char c) {

        if (Arrays.asList('{', '}', '(', ')', '[', ']').contains(c))
            return -1;
        if (Arrays.asList('+', '-').contains(c))
            return 1;
        if (Arrays.asList('*', '/').contains(c))
            return 2;
        if ('^' == c)
            return 3;

        return 99;
    }

    public static String convert(String expression) {

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(char c: expression.toCharArray()) {
            if (Character.isLetter(c)) {
                postfix.append(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            }
            else {
                while(!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while(!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        //String expr = "(a + b * (d - e))";
        String expr = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(convert(expr));
    }
}

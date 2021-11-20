package arrays.stack;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvaluateInfixUsingStack {

    public static final List<Character> allOperators = Arrays.asList('+', '-', '*', '/');

    public static boolean isOperator(char c) {
        return allOperators.contains(c);
    }

    private static final Stack<Integer> operand = new Stack<>();
    private static final Stack<Character> operator = new Stack<>();

    public static int evaluateOperator(char c, int left, int right) {
        return switch (c) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> left / right;
            default -> -1;
        };
    }

    public static int evaluate(String infix) {

        for (int i = 0; i < infix.length() - 1; i++) {
            char c = infix.charAt(i);
            char c2 = infix.charAt(i + 1);

            if (isOperator(c)) {
                operand.push(evaluateOperator(c, operand.pop(), c2 - '0'));
                i++;
            }
            else if (Character.isDigit(c2)) {
                operand.push(c2 - '0');
            }
            else if (Character.isDigit(c)) {
                operand.push(c - '0');
            }
        }

        while (!operator.isEmpty()) {
            int left = operand.pop(), right = operand.pop();
            char operation = operator.pop();
            operand.push(evaluateOperator(operation, left, right));
        }

        return !operand.isEmpty() ? operand.pop() : -1;
    }

    public static void main(String[] args) {

        System.out.println(EvaluateInfixUsingStack.evaluate("(1+2)*(3/3)"));
    }


}

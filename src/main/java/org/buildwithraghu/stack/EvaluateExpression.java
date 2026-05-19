package org.buildwithraghu.stack;

import java.util.*;

public class EvaluateExpression {

    public static Map<Character, Integer> operators = Map.of(
            '+', 1,
            '-', 1,
            '*', 2,
            '/', 2
    );

    // Defines which operators happen first
    public int precedence(char op) {
        return operators.getOrDefault(op, -1);
    }

    public Integer process(char op, int left, int right) {
        return switch (op) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> {
                if (right == 0) throw new ArithmeticException("Division by zero");
                yield left / right;
            }
            default -> 0;
        };
    }

    public Integer calculate(String expr) {
        Stack<Character> operator = new Stack<>();
        Stack<Integer> operand = new Stack<>();

        for(int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // 1. Handle Multi-digit Numbers
            if (Character.isDigit(c)) {
                int val = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    val = (val * 10) + (expr.charAt(i) - '0');
                    i++;
                }
                operand.push(val);
                i--; // Step back because the for-loop increments i
            }
            // 2. Handle Opening Brackets
            else if (c == '(') {
                operator.push(c);
            }
            // 3. Handle Closing Brackets: Solve everything inside
            else if (c == ')') {
                while (operator.peek() != '(') {
                    operand.push(process(operator.pop(), operand.pop(), operand.pop()));
                }
                operator.pop(); // Remove '('
            }
            else if (operators.containsKey(c)) {
                while(precedence(operator.peek()) >= precedence(c)) {
                    int right = operand.pop();
                    int left = operand.pop();
                    operand.push(process(operator.pop(), left, right));
                }
                operator.push(c);
            }

            // Final clear-out of the stacks
            while (!operand.isEmpty() && !operator.isEmpty()) {
                int right = operand.pop();
                int left = operand.pop();
                operand.push(process(operator.pop(), left, right));
            }

            return operand.pop();
        }
        return operand.peek();
    }

    public static void main(String[] args) {
        EvaluateExpression ex = new EvaluateExpression();
        System.out.println(ex.calculate("(1+2)*0"));
    }
}

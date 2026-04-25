package arrays.stack;

/*
 * created by raghavendra.ta on 05-Jul-2021
 */

import java.util.Stack;

/**
 * iterate each character
 *
 * isNumber(c)  => stack.push(int(c))
 * operand      => A, B = stack.pop(), stack.pop()
 *              => stack.push(A operand B)
 *
 * return stack.pop()
 *
 * space O(n), complexity O(n)
 */


public class EvaluatePostfix {

    public static void evaluate(String postfix) {

        Stack<Integer> stack = new Stack<>();

        for(char c: postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            }
            else {
                int a = stack.pop(), b = stack.pop();
                switch (c) {
                    case '+' -> stack.push(a + b);
                    case '-' -> stack.push(a - b);
                    case '*' -> stack.push(a * b);
                    case '/' -> stack.push(a / b);
                }
            }
        }

        System.out.println(stack.pop());
    }

    public static void main(String[] args) {
        String infix = "4+(5*6)*2+9";
        String postfix = InfixToPostfix.convert(infix);
        evaluate(postfix);
    }

}

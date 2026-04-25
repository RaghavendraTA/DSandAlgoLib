package org.buildwithraghu.google;

import java.util.Map;
import java.util.Stack;

class ValidParentheses_20 {
	
	private static final Map<Character, Character> map = Map.of('(', ')', '[', ']', '{', '}');
	
	public boolean isValid(String s) {
		Stack<Character> stk = new Stack<>();
        for(char c: s.toCharArray()) {
			if (c == '(' || c == '[' || c == '{')
				stk.push(map.get(c));
			else if(!stk.isEmpty() && stk.peek() == c)
				stk.pop();
            else
                return false;
		}
		return stk.isEmpty();
    }

	public static void main(String[] args) {
		ValidParentheses_20 s = new ValidParentheses_20();
		System.out.println("Testing");
	}
}
package org.buildwithraghu.stack;

import java.util.Stack;

public class LongestValidParentheses {

    // https://leetcode.com/problems/longest-valid-parentheses/
    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push(i);
            } else if (!stk.isEmpty()) {
                stk.pop();
                if (!stk.isEmpty())
                    ans = Math.max(ans, i-stk.peek());
                else
                    stk.push(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = ")()())";
        int ans = new LongestValidParentheses().longestValidParentheses(str);
        System.out.println(ans);
    };
}

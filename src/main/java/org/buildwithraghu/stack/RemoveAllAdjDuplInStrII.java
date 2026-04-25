package org.buildwithraghu.stack;

import java.util.Stack;

public class RemoveAllAdjDuplInStrII {

    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stk = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            int j = i+1, cnt = 1;
            int c = s.charAt(i);
            while(j < s.length() && c == s.charAt(j)) {
                cnt++;
                j++;
            }
            cnt %= k;
            while (!stk.isEmpty() && stk.peek()[0] == c) {
                int[] p = stk.pop();
                cnt += p[1];
                cnt %= k;
            }
            if (stk.isEmpty() || cnt > 0)
                stk.push(new int[]{c, cnt});
            i = j - 1;
        }
        StringBuilder stb = new StringBuilder();
        while(!stk.isEmpty()) {
            int[] p = stk.pop();
            stb.repeat((char)p[0], p[1]);
        }
        return stb.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveAllAdjDuplInStrII ra = new RemoveAllAdjDuplInStrII();
        System.out.println(ra.removeDuplicates("abcd", 1));
    }
}

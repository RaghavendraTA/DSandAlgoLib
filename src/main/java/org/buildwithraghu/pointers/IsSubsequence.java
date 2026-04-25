package org.buildwithraghu.pointers;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        else if (t.isEmpty()) return false;
        int i = 0, j = 0;
        while(i < t.length() && j < s.length()) {
            if (t.charAt(i) == s.charAt(j))
                j++;
            i++;
        }
        return j == s.length();
    }
}

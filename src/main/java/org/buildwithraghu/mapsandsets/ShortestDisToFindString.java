package org.buildwithraghu.mapsandsets;

public class ShortestDisToFindString {

    // https://leetcode.com/problems/shortest-distance-to-target-string-in-a-circular-array
    public int closestTarget(String[] words, String target, int startIndex) {
        if (words[startIndex].equals(target))
            return 0;
        int l = startIndex, r = startIndex;
        while(!words[l].equals(target)) {
            l--;
            if (l < 0) l = words.length-1;
            if (l == startIndex) break;
        }
        while(!words[r].equals(target)) {
            r++;
            if (r >= words.length) r = 0;
            if (r == startIndex) break;
        }
        int ans = Integer.MAX_VALUE;
        if (l < startIndex)
            ans = startIndex-l;
        else if (l > startIndex)
            ans = words.length-l+startIndex;
        if (r < startIndex)
            ans = Math.min(ans, words.length-startIndex+r);
        else if (r > startIndex)
            ans = Math.min(ans, r-startIndex);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        String[] words = {"hello","i","am","leetcode","hello2"};
        String target = "hello";
        int startIndex = 3;
        ShortestDisToFindString s = new ShortestDisToFindString();
        System.out.println(s.closestTarget(words, target, startIndex));
    }
}

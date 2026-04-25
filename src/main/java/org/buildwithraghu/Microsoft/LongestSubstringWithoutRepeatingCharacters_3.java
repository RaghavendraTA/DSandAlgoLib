import java.util.HashMap;

class Solution {
	
    public int lengthOfLongestSubstring(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int l = 0, maxLen = 0;
        for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			if (map.containsKey(c)) {
				l = Math.max(l, map.get(c) + 1);
			}
			//System.out.println("l = " + l);
			map.put(c, r);
			maxLen = Math.max(maxLen, r - l + 1);
		}
		return maxLen;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.lengthOfLongestSubstring("abba"));
	}
}
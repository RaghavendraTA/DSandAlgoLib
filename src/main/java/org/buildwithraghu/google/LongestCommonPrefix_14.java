package org.buildwithraghu.google;

class LongestCommonPrefix_14 {
	
	public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder(strs[0]);
		for(int j = 1; j < strs.length; j++) {
			String s = strs[j];
			int i = 0;
			for(char c: s.toCharArray()) {
				if (i < sb.length() && sb.charAt(i) == c)
					i++;
				else
                    break;
			}
			sb.delete(i, sb.length());
		}
		return sb.toString();
    }

	public static void main(String[] args) {
		LongestCommonPrefix_14 s = new LongestCommonPrefix_14();
		System.out.println("Testing");
	}
}
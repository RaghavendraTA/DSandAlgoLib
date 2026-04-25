package org.buildwithraghu.google;

class TimeNeededtoRearrangeaBinaryString_2380 {
	
	public int secondsToRemoveOccurrences(String s) {
        StringBuilder sb = new StringBuilder(s);
		int t = 0;
		while(true) {
			boolean flag = false;
			for(int i = 0; i < s.length()-1; i++) {
				if (sb.charAt(i) == '0' && sb.charAt(i+1) == '1') {
					flipChars(sb);
					flag = true;
					t++;
				}
			}
			if (!flag) break;
		}
		return t;
    }
	
	private void flipChars(StringBuilder sb) {
		for(int i = 0; i < sb.length()-1; i++) {
			if (sb.charAt(i) == '0' && sb.charAt(i+1) == '1') {
				sb.setCharAt(i, '1');
				sb.setCharAt(i+1, '0');
				i++;
			}
		}
	}

	public static void main(String[] args) {
		TimeNeededtoRearrangeaBinaryString_2380 s = new TimeNeededtoRearrangeaBinaryString_2380();
		int ans = s.secondsToRemoveOccurrences("11100");
		System.out.println(ans);
	}
}
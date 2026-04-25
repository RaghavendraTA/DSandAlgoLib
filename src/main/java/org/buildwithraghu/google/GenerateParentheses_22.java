package org.buildwithraghu.google;

import java.util.ArrayList;
import java.util.List;

class GenerateParentheses_22 {
	
	public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();
        List<String> start = new ArrayList<>();
        start.add("");
		dp.add(start);
		
		for(int i = 1; i <= n; i++) {
            dp.add(new ArrayList<>());
			for(int j = 0; j < i; j++) {
				List<String> left = dp.get(j);
				List<String> right = dp.get(i-j-1);
				for(int k = 0; k < left.size(); k++) {
					for(int l = 0; l < right.size(); l++) {
						dp.get(i).add("(" + left.get(k) + ")" + right.get(l));
					}
				}
			}
		}
		return dp.get(n);
    }

	public static void main(String[] args) {
		GenerateParentheses_22 s = new GenerateParentheses_22();
		System.out.println("Testing");
	}
}
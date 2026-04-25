package org.buildwithraghu.dynamicprogramming;

public class Fibonacci {
	
	public static void generate(int n) {
		int f = 0, s = 1, t;
		System.out.print("[0, 1");
		for(int i = 2; i < n; i++) {
			t = f + s;
			f = s;
			s = t;
			System.out.print(", " + t);
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		generate(8);
	}
}
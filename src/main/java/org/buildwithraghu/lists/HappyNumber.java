package org.buildwithraghu.lists;

public class HappyNumber {

    // https://leetcode.com/problems/happy-number/
    public boolean isHappy(int n) {
        while(n != 1 && n != 4) {
            int ans = 0;
            while(n > 0) {
                int d = (n % 10);
                ans += d * d;
                n /= 10;
            }
            n = ans;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(5));
    }
}

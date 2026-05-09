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

    // The fast and slow pointer solution
    public boolean isHappyPtr(int n) {
        int slow = n, fast = n;
        while (true) {
            slow = getNextNum(slow);
            fast = getNextNum(getNextNum(fast));
            if (fast == 1)
                return true;
            else if (fast == slow)
                return false;
        }
    }

    int getNextNum(int x) {
        int next_num = 0, digit;
        while (x > 0) {
            digit = x % 10;
            x = x / 10;
            next_num += digit * digit;
        }
        return next_num;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(5));
    }
}

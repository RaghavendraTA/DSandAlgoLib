package org.buildwithraghu.google;

public class FindTheCelebrity_277 {

    // useless just to prevent build failure
    private boolean knows(int a, int b) {
        return true;
    }

    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        return isCelebrity(n, candidate) ? candidate : -1;
    }

    private boolean isCelebrity(int n, int candidate) {
        for(int i = 0; i < n; i++) {
            if (i == candidate) continue;
            if (knows(candidate, i) || !knows(i, candidate))
                return false;
        }
        return true;
    }
}

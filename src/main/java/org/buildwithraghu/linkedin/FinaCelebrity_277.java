package org.buildwithraghu.linkedin;

public class FinaCelebrity_277 {

    // Mock function
    private boolean knows(int i, int j) {
        return true;
    }

    int n = 0;

    public int findCelebrity(int n) {
        this.n = n;
        int candidate = 0;
        for(int i = 0; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        if (isCelebrity(candidate))
            return candidate;
        return -1;
    }

    private boolean isCelebrity(int i) {
        for(int j = 0; j < n; j++) {
            if (i == j) continue;
            if (knows(i, j) || !knows(j, i))
                return false;
        }
        return true;
    }
}

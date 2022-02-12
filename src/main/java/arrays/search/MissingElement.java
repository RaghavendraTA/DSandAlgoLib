package arrays.search;

import java.util.*;

public class MissingElement {

    // Without sorting !
    public int findMissingElement(int[] A) {
        if (A.length == 0)
            return 1;
        if (A.length == 1)
            return A[0] != 1 ? 1 : 2;
        Map<Integer, Integer> occ = new HashMap<>();
        for(int i: A) {
            occ.putIfAbsent(i, 1);
            occ.computeIfPresent(i, (key, value) -> value + 1);
        }
        for(int i = 1; i <= A.length; i++) {
            if (!occ.containsKey(i)) {
                return i;
            }
        }
        return A.length + 1;
    }
}

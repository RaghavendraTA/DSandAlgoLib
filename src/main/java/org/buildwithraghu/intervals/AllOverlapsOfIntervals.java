package org.buildwithraghu.intervals;

import java.util.ArrayList;
import java.util.List;

public class AllOverlapsOfIntervals {

    public List<int[]> identifyAllIntervalOverlaps(int[][] intervals1, int[][] intervals2) {
        List<int[]> overlaps = new ArrayList<>();
        int i = 0, j = 0;
        while(i < intervals1.length && j < intervals2.length) {
            int[] A, B;
            if (intervals1[i][0] <= intervals2[j][0]) {
                A = intervals1[i];
                B = intervals2[j];
            } else {
                B = intervals1[i];
                A = intervals2[j];
            }
            if (A[1] >= B[0])
                overlaps.add(new int[]{B[0], Math.min(A[1], B[1])});

            if (intervals1[i][1] < intervals2[j][1])
                i++;
            else
                j++;
        }
        return overlaps;
    }
}

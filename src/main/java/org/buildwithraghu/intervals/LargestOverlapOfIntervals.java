package org.buildwithraghu.intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LargestOverlapOfIntervals {

    public int LargestOverlapOfIntervals(int[][] intervals) {
        List<int[]> points = new ArrayList<>();
        for(int[] interval: intervals) {
            points.add(new int[]{interval[0], 'S'});
            points.add(new int[]{interval[1], 'E'});
        }
        points.sort(Comparator.comparingInt(x -> x[0]));
        int activeInterval = 0, maxOverlaps = 0;
        for(int[] p: points) {
            activeInterval += p[1] == 'S' ? 1 : -1;
            maxOverlaps = Math.max(maxOverlaps, activeInterval);
        }
        return maxOverlaps;
    }
}

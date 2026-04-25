package org.buildwithraghu.intervals;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));
        int c = 0;
        int[] lastInterval = new int[]{};
        for(int[] interval: intervals) {
            if (lastInterval.length == 0) {
                lastInterval = interval;
                continue;
            }
            if (lastInterval[1] > interval[0])
                c++;
            else
                lastInterval = interval;
        }
        return c;
    }
}

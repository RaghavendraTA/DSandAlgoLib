package org.buildwithraghu.intervals;

import java.util.*;

public class MeetingRooms {

    // https://leetcode.com/problems/meeting-rooms
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0) return true;
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        int[] left = intervals[0];
        for(int i = 1; i < intervals.length; i++) {
            if (left[1] > intervals[i][0])
                return false;
            left = intervals[i];
        }
        return true;
    }

    // https://leetcode.com/problems/meeting-rooms-ii/
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int count = 0;

        for(int[] interval: intervals) {
            int i = interval[0], j = interval[1];
            if (queue.isEmpty()) {
                queue.offer(j);
                continue;
            }
            while(!queue.isEmpty() && i >= queue.peek()) {
                queue.poll();
            }
            queue.offer(j);
            count = Math.max(count, queue.size());
        }

        return Math.max(count, queue.size());
    }
}

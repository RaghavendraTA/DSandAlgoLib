package org.buildwithraghu.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms12 {

    // https://leetcode.com/problems/meeting-rooms/
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        for(int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1])
                return false;
        }
        return true;
    }

    // https://leetcode.com/problems/meeting-rooms-ii
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int firstEnd = intervals[0][1];
        pq.offer(firstEnd);

        for(int i = 1; i < intervals.length; ++i) {
            if(intervals[i][0] >= pq.peek())
                pq.poll();
            pq.offer(intervals[i][1]);
        }

        return pq.size();
    }
}

package leetcode.array;

/*
 * created by raghavendra.ta on 17-Jul-2021
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int count = 0;

        for(int[] arr: intervals) {
            int start = arr[0], end = arr[1];
            if (queue.isEmpty()) {
                queue.add(end);
                continue;
            }
            while (!queue.isEmpty() && start >= queue.peek()) {
                queue.poll();
            }
            queue.add(end);
            count = Math.max(count, queue.size());
        }

        return Math.max(count, queue.size());
    }

    public static void main(String[] args) {
        int[][] input = {{0,30},{5,10},{15,20}};
        new MeetingRoomsII().minMeetingRooms(input);
    }
}

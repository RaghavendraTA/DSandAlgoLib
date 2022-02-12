package microsoft;

import java.util.Collections;
import java.util.PriorityQueue;

public class Filter {

    public int solution(int[] A) {
        PriorityQueue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());
        double sum = 0;
        int filter = 0;
        for(int i: A) {
            queue.add((double)i);
            sum += i;
        }
        double totalSum = sum;
        sum /= 2;
        while(totalSum > sum && !queue.isEmpty()) {
            double temp = queue.poll();
            totalSum -= temp;
            temp /= 2;
            totalSum += temp;
            queue.add(temp);
            filter++;
        }
        return filter;
    }

    public static void main(String[] args) {
        System.out.println(new Filter().solution(new int[]{1, 2}));
    }
}

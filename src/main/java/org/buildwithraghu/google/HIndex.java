package google;

import java.util.*;

public class HIndex {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            System.out.print("Case #" + t + ": ");
            int n = sc.nextInt();
            int hIndex = 0;
            for(int i = 1; i <= n; i++) {
                int p = sc.nextInt();
                if (p > hIndex) {
                    queue.add(p);
                }
                while (!queue.isEmpty() && queue.peek() <= hIndex) {
                    queue.poll();
                }
                if (queue.size() >= hIndex + 1)
                    hIndex++;
                System.out.print(hIndex + " ");
            }
            System.out.println();
        }
    }
}

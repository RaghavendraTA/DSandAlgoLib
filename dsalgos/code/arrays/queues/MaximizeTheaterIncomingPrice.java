package arrays.queues;

/*
 * created by raghavendra.ta on 26-Jul-2021
 */

import java.util.PriorityQueue;

public class MaximizeTheaterIncomingPrice {

    static class Pair {
        int seatNo;
        int price;
        int remainingSeats;
        Pair(int seatNo, int price, int remainingSeats) {
            this.price = price;
            this.seatNo = seatNo;
            this.remainingSeats = remainingSeats;
        }
    }

    public int getPrice(int seatNo, int remainingSeats) {
        if (seatNo % 2 == 0) {
            return remainingSeats - seatNo;
        } else {
            return 100 - seatNo;
        }
    }

    /**
     * Condition 1: if the seat is odd then price is 100 - currentSeat
     * Condition 2: if the seat is even then price is remainingSeatsInThatRow - currentSeat
     * @param A
     */
    public void maximize(int[] A, int n) {

        PriorityQueue<Pair> heap = new PriorityQueue<>((leftPair, rightPair) -> rightPair.price - leftPair.price);

        for(int i: A) {
            heap.add(new Pair(1, 99, i));
        }

        int i = 0, sum = 0;
        while(!heap.isEmpty() && i < n) {
            Pair p = heap.poll();
            sum += p.price;
            if (p.seatNo <= p.remainingSeats) {
                heap.add(new Pair(p.seatNo + 1,
                                  getPrice(p.seatNo + 1, p.remainingSeats),
                                  p.remainingSeats - 1));
            }
            i++;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        new MaximizeTheaterIncomingPrice().maximize(new int[]{10, 10, 30, 40}, 4);
    }
}

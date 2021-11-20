package arrays.matrix;

/*
 * created by raghavendra.ta on 21-Jul-2021
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This program is not working find different solution,
 * there is solution using Dynamic programming
 */
public class NumberOfMovesForKnight {

    static class CellDetails {
        int x, y, steps;
        double distance;
        CellDetails(int x, int y, int steps, double distance) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.distance = distance;
        }
    }

    public double getDistance(int x1, int y1, int x2, int y2) {
        double x = (x1 - x2);
        double y = (y1 - y2);
        return Math.sqrt((x * x) + (y * y));
    }

    public boolean isValidCell(int x, int y) {
        if (x < 0 || x >= 8)
            return false;
        return y >= 0 && y < 8;
    }

    public void printGrid(int kx, int ky, int dx, int dy) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (i == kx && j == ky) {
                    System.out.print("K ");
                }
                else if (i == dx && j == dy) {
                    System.out.print("D ");
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param kx        knight x coordinate
     * @param ky        knight y coordinate
     * @param dx        destination x coordinate
     * @param dy        destination y coordinate
     */
    public void noOfMovesRequiredByKnightToReachACell(int kx, int ky, int dx, int dy) {

        PriorityQueue<CellDetails> queue = new PriorityQueue<>(Comparator.comparingDouble(c -> c.distance));
        int[][] possibleMoves = {{2, 1}, {2, -1}, {-1, 2}, {1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}};
        boolean[][] visited = new boolean[8][8];
        for(int i = 0; i < 8; i++)
            Arrays.fill(visited[i], false);
        visited[kx][ky] = true;
        printGrid(kx, ky, dx, dy);

        queue.add(new CellDetails(kx, ky, 0, getDistance(kx, ky, dx, dy)));

        while(!queue.isEmpty()) {
            CellDetails c = queue.poll();
            int currentX = c.x;
            int currentY = c.y;
            int steps = c.steps;
            //printGrid(currentX, currentY, dx, dy);
            if (currentX == dx && currentY == dy) {
                System.out.println("Number of steps required to reach the destination = " + steps);
                return;
            }
            for(int[] move: possibleMoves) {
                int newX = currentX + move[0];
                int newY = currentY + move[1];
                if (isValidCell(newX, newY) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new CellDetails(newX, newY, steps + 1, getDistance(newX, newY, dx, dy)));
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfMovesForKnight N = new NumberOfMovesForKnight();
        N.noOfMovesRequiredByKnightToReachACell(4, 0, 7, 1);
    }
}

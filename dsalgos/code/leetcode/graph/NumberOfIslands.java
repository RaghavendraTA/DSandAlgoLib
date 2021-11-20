package leetcode.graph;

/*
 * created by raghavendra.ta on 16-Jul-2021
 */

import java.util.Stack;

public class NumberOfIslands {

    static class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void markNeighbours(char[][] grid, int idx, int jdx) {

        int m = grid.length, n = grid[0].length;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(idx, jdx));

        while (!stack.isEmpty()) {

            Pair pair = stack.pop();
            int i = pair.i, j = pair.j;
            grid[i][j] = '#';

            if (i + 1 < m && grid[i + 1][j] == '1') {
                stack.push(new Pair(i + 1, j));
            }
            if (j + 1 < n && grid[i][j + 1] == '1') {
                stack.push(new Pair(i, j + 1));
            }
            if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                stack.push(new Pair(i - 1, j));
            }
            if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                stack.push(new Pair(i, j - 1));
            }
        }

    }

    public static int numIslands(char[][] grid) {

        int m = grid.length, n = grid[0].length;

        int islandCounter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    markNeighbours(grid, i, j);
                    islandCounter++;
                }
            }
        }

        return islandCounter;
    }


    public static void main(String[] args) {

        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '0'},
                {'0', '0', '0', '0', '1'}
        };

        char[][] grid2 = {{'1'}};

        char[][] grid3 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'},
        };

        System.out.println(numIslands(grid));
        System.out.println(numIslands(grid2));
        System.out.println(numIslands(grid3));
    }

}

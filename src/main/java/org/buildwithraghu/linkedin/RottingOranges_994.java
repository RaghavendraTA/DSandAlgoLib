package org.buildwithraghu.linkedin;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges_994 {

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0], j = p[1], newValue = 1 + grid[i][j];
            if (i > 0 && grid[i-1][j] == 1) {
                grid[i - 1][j] = newValue;
                ans = Math.max(newValue, ans);
                queue.add(new int[]{i-1, j});
            }
            if (j > 0 && grid[i][j-1] == 1) {
                grid[i][j - 1] = newValue;
                ans = Math.max(newValue, ans);
                queue.add(new int[]{i, j-1});
            }
            if (j < n-1 && grid[i][j+1] == 1) {
                grid[i][j + 1] = newValue;
                ans = Math.max(newValue, ans);
                queue.add(new int[]{i, j+1});
            }
            if (i < m-1 && grid[i+1][j] == 1) {
                grid[i + 1][j] = newValue;
                ans = Math.max(newValue, ans);
                queue.add(new int[]{i+1, j});
            }
        }

        boolean setFlag = true;
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) {
                    setFlag = false;
                    break;
                }
            }
        }

        return (!setFlag) ? -1 : (ans == Integer.MIN_VALUE ? 0 : (ans - 2));
    }
}

package org.buildwithraghu.dynamicprogramming;

public class PaintHouse {

    // https://leetcode.com/problems/paint-house/
    public int minCost(int[][] costs) {
        int n = costs.length;
        for (int i = 1; i < n; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(costs[n - 1][0], Math.min(costs[n - 1][1], costs[n - 1][2]));
    }

    // --------------------------------------------------------------------------------------

    // https://leetcode.com/problems/paint-house-ii/description/
    public int minCostII(int[][] costs) {
        int m = costs.length, n = costs[0].length;
        int min1 = 0, min2 = 0, min1Color = -1;

        for (int i = 0; i < m; i++) {
            int newMin1 = Integer.MAX_VALUE, newMin2 = Integer.MAX_VALUE, newMin1Color = -1;

            for (int j = 0; j < n; j++) {
                int cost = costs[i][j] + (j == min1Color ? min2 : min1);
                if (cost < newMin1) {
                    newMin2 = newMin1;
                    newMin1 = cost;
                    newMin1Color = j;
                } else if (cost < newMin2) {
                    newMin2 = cost;
                }
            }

            min1 = newMin1;
            min2 = newMin2;
            min1Color = newMin1Color;
        }

        return min1;
    }
}

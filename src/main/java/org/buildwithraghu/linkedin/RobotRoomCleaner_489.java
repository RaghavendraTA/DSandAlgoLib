package org.buildwithraghu.linkedin;

import org.buildwithraghu.utils.Pair;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner_489 {

    interface Robot {
        public boolean move();
        public void turnLeft();
        public void turnRight();
        public void clean();
    }

    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    Robot robot;

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public void backTrack(int row, int col, int d) {
        visited.add(new Pair<>(row, col));
        robot.clean();
        for(int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            int newRow = row + direction[newD][0];
            int newCol = col + direction[newD][1];
            if (!visited.contains(new Pair<>(newRow, newCol)) && robot.move()) {
                backTrack(newRow, newCol, newD);
                goBack();
            }
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backTrack(0, 0, 0);
    }
}

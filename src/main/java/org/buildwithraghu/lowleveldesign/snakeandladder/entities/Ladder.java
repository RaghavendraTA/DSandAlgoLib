package org.buildwithraghu.lowleveldesign.snakeandladder.entities;

public class Ladder extends BoardEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) {
            throw new IllegalArgumentException("Ladder head should start from bottom");
        }
    }
}

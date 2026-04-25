package org.buildwithraghu.lowleveldesign.snakeandladder.entities;

import lombok.Getter;

@Getter
abstract public class BoardEntity {
    private final int start;
    private final int end;

    public BoardEntity(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

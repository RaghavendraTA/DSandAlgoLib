package org.buildwithraghu.lowleveldesign.snakeandladder.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final int size;

    private final Map<Integer, Integer> snakeAndLadders;

    public int getSize() {
        return size;
    }

    public Board(int size, List<BoardEntity> entities) {
        this.size = size;
        this.snakeAndLadders = new HashMap<>();

        for(BoardEntity entity: entities) {
            snakeAndLadders.put(entity.getStart(), entity.getEnd());
        }
    }

    public int getFinalPosition(int pos) {
        return snakeAndLadders.getOrDefault(pos, pos);
    }
}

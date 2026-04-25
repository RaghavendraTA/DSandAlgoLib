package org.buildwithraghu.lowleveldesign.snakeandladder.entities;

import lombok.Data;

@Data
public class Player {

    private String name;
    private int currentPos;

    public Player(String name) {
        this.name = name;
    }
}

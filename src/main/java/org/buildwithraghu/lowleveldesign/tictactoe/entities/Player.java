package org.buildwithraghu.lowleveldesign.tictactoe.entities;

import lombok.Getter;

@Getter
public class Player {

    private final String playerName;

    private final Symbol playerSymbol;

    public Player(String playerName, Symbol playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }
}

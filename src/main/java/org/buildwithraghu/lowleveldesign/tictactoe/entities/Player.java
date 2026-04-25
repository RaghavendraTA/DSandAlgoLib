package org.buildwithraghu.lowleveldesign.tictactoe.entities;

public class Player {

    private final String playerName;

    private final Symbol playerSymbol;

    public Player(String playerName, Symbol playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }
}

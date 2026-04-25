package org.buildwithraghu.lowleveldesign.tictactoe.entities;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY(' ');

    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getChar() {
        return this.symbol;
    }
}

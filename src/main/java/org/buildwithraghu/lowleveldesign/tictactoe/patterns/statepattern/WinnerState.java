package org.buildwithraghu.lowleveldesign.tictactoe.patterns.statepattern;

import org.buildwithraghu.lowleveldesign.tictactoe.TicTacToe;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Player;
import org.buildwithraghu.lowleveldesign.tictactoe.exceptions.InvalidMoveException;

public class WinnerState implements GameState {
    @Override
    public void handleMove(Board board, Player player, TicTacToe system, int pos) {
        throw new InvalidMoveException("Game is already over. " + board.getWinner().getPlayerName() + " has won.");
    }
}

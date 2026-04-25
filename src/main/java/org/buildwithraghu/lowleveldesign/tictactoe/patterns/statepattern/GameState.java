package org.buildwithraghu.lowleveldesign.tictactoe.patterns.statepattern;

import org.buildwithraghu.lowleveldesign.tictactoe.TicTacToe;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Player;

public interface GameState {
    void handleMove(Board board, Player player, TicTacToe system, int pos);
}

package org.buildwithraghu.lowleveldesign.tictactoe.patterns.strategypatterns;

import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player);
}

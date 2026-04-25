package org.buildwithraghu.lowleveldesign.tictactoe.patterns.strategypatterns;

import org.buildwithraghu.lowleveldesign.tictactoe.entities.*;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player);
}

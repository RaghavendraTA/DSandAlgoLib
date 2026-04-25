package org.buildwithraghu.lowleveldesign.tictactoe.patterns.strategypatterns;

import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Player;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Player player) {
        Symbol[][] cell2d = board.getBoard();
        boolean notWon = false;
        for(int i = 0; i < cell2d.length; i++) {
            if (cell2d[i][i] != player.getPlayerSymbol()) {
                notWon = true;
                break;
            }
        }
        if (!notWon) return true;

        notWon = false;
        for(int i = cell2d.length-1; i >= 0; i--) {
            if (cell2d[i][i] != player.getPlayerSymbol()) {
                notWon = true;
                break;
            }
        }
        return !notWon;
    }
}

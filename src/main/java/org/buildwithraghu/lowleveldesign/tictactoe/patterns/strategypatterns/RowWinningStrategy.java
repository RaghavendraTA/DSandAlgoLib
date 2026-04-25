package org.buildwithraghu.lowleveldesign.tictactoe.patterns.strategypatterns;

import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Player;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Symbol;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Player player) {
        Symbol[][] cell2d = board.getBoard();
        for(int i = 0; i < cell2d.length; i++) {
            boolean notWon = false;
            for(int j = 0; j < cell2d[0].length; j++) {
                if (cell2d[i][j] != player.getPlayerSymbol()) {
                    notWon = true;
                }
            }
            if (!notWon) return true;
        }
        return false;
    }
}

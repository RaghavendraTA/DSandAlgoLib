package org.buildwithraghu.lowleveldesign.tictactoe.patterns.statepattern;

import org.buildwithraghu.lowleveldesign.tictactoe.TicTacToe;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.GameStatus;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Player;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.Symbol;

public class InProgressState implements GameState {
    @Override
    public void handleMove(Board board, Player player, TicTacToe tsystem, int pos) {
        board.setMove(player, pos-1);
        if (board.checkWinner(player)) {
            board.setWinner(player);
            board.setStatus(player.getPlayerSymbol() == Symbol.X ? GameStatus.WINNER_X : GameStatus.WINNER_Y);
            board.setState(new WinnerState());
        } else if (!board.hasMove()) {
            board.setStatus(GameStatus.DRAW);
            board.setState(new DrawState());
        } else {
            tsystem.switchPlayers();
        }
    }
}

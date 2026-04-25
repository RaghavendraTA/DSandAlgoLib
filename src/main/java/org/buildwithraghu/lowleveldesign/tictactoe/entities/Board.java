package org.buildwithraghu.lowleveldesign.tictactoe.entities;

import lombok.Getter;
import lombok.Setter;
import org.buildwithraghu.lowleveldesign.tictactoe.TicTacToe;
import org.buildwithraghu.lowleveldesign.tictactoe.patterns.observerpattern.GameSubject;
import org.buildwithraghu.lowleveldesign.tictactoe.patterns.statepattern.GameState;
import org.buildwithraghu.lowleveldesign.tictactoe.patterns.statepattern.InProgressState;
import org.buildwithraghu.lowleveldesign.tictactoe.patterns.strategypatterns.*;

import java.util.List;

public class Board extends GameSubject {

    @Getter
    private Symbol[][] board;

    @Getter @Setter
    private GameStatus status;

    @Getter @Setter
    private Player winner;

    @Getter @Setter
    private GameState state;

    private final List<WinningStrategy> strategies = List.of(
        new RowWinningStrategy(),
        new ColumnWinningStrategy(),
        new DiagonalWinningStrategy()
    );

    public void init() {
        board = new Symbol[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = Symbol.EMPTY;
            }
        }
        state = new InProgressState();
        status = GameStatus.IN_PROGRESS;
        winner = null;
    }

    public void setMove(Player player, int pos) {
        int i = pos / 3, j = (pos % 3);
        board[i][j] = player.getPlayerSymbol();
    }

    public void makeMove(Player player, TicTacToe tsystem, int pos) {
        state.handleMove(this, player, tsystem, pos);
    }

    public boolean hasMove() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (board[i][j] == Symbol.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPositionTaken(int pos) {
        int i = pos / 3, j = (pos % 3);
        return board[i][j] != Symbol.EMPTY;
    }

    public boolean checkWinner(Player player) {
        for(WinningStrategy strategy: strategies) {
            if (strategy.checkWinner(this, player)) {
                return true;
            }
        }
        return false;
    }

    public void printBoard() {
        StringBuilder builder = new StringBuilder();
        builder.append("+ - + - + - +\n");
        for(int i = 0; i < 3; i++) {
            builder.append("|");
            for(int j = 0; j < 3; j++) {
                String local = board[i][j] == Symbol.EMPTY ? " " : board[i][j].name();
                builder.append(" ").append(local).append(" |");
            }
            builder.append("\n+ - + - + - +\n");
        }
        System.out.println(builder);
    }
}

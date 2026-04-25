package org.buildwithraghu.lowleveldesign.tictactoe;

import lombok.Getter;
import lombok.Setter;
import org.buildwithraghu.lowleveldesign.tictactoe.entities.*;

import java.util.Scanner;

public class TicTacToe {

    private final Board board = new Board();

    @Getter @Setter
    private Player curPlayer;

    private Player firstPlayer, secondPlayer;

    private final Scanner sc = new Scanner(System.in);

    private Player createPlayer(String num, Symbol selected) {
        System.out.print("Enter " + num + " play name: ");
        String name = sc.next();
        String symbol;
        if (selected == null) {
            System.out.print("Enter " + num + " player symbol between X or O: ");
            symbol = sc.next();
        } else {
            symbol = selected.ordinal() == 0 ? "O" : "X";
        }
        if (!symbol.equals("X") && !symbol.equals(("O"))) {
            System.out.println("Invalid input, retry");
            createPlayer(num, selected);
        }
        return new Player(name, Symbol.valueOf(symbol));
    }

    public void switchPlayers() {
        setCurPlayer(curPlayer == firstPlayer ? secondPlayer : firstPlayer);
    }

    private void play() {
        while(true) {
            board.init();

            firstPlayer = createPlayer("first", null);
            secondPlayer = createPlayer("second", firstPlayer.getPlayerSymbol());
            setCurPlayer(firstPlayer);

            while(board.getStatus() == GameStatus.IN_PROGRESS) {
                System.out.print(curPlayer.getPlayerName() + ", Pick an option between 1-9: ");
                int pos = sc.nextInt();
                board.makeMove(curPlayer, this, pos);
                board.printBoard();
            }

            if (board.getStatus() != GameStatus.DRAW) {
                System.out.println(board.getWinner().getPlayerName() + " won the round");
            } else {
                System.out.println("This round was a draw");
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}

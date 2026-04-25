package org.buildwithraghu.lowleveldesign.snakeandladder;

import lombok.Getter;
import org.buildwithraghu.lowleveldesign.snakeandladder.entities.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private GameStatus status;
    private Player winner;

    static class GameBuilder {
        private Board board;
        private Queue<Player> players;
        private Dice dice;

        public GameBuilder setBoard(int boardSize, List<BoardEntity> boardEntities) {
            this.board = new Board(boardSize, boardEntities);
            return this;
        }

        public GameBuilder setPlayers(List<String> playernames) {
            this.players = new LinkedList<>();
            for(String playername: playernames) {
                players.add(new Player(playername));
            }
            return this;
        }

        public GameBuilder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Game build() {
            if (board == null || players == null || dice == null) {
                throw new IllegalArgumentException("Board, players and Dic must be set.");
            }
            return new Game(this);
        }

        public Board getBoard() {
            return board;
        }

        public Queue<Player> getPlayers() {
            return players;
        }

        public Dice getDice() {
            return dice;
        }
    }

    private Game(GameBuilder builder) {
        this.board = builder.getBoard();
        this.players = new LinkedList<>(builder.getPlayers());
        this.dice = builder.getDice();
        this.status = GameStatus.NOT_STARTED;
    }

    private void takeTurn(Player player) {
        int roll = dice.roll();
        System.out.println(player.getName() + "'s turn, Dice Rolled: " + roll);

        int curPosition = player.getCurrentPos();
        int nextPosition = curPosition + roll;

        if (nextPosition > board.getSize()) {
            System.out.println(player.getName() + " Turn skipped\n");
            return;
        }

        if (nextPosition == board.getSize()) {
            player.setCurrentPos(nextPosition);
            this.winner = player;
            this.status = GameStatus.FINISHED;

            System.out.println(player.getName() + " won the game");
            return;
        }

        int finalPosition = board.getFinalPosition(nextPosition);

        if (finalPosition > nextPosition) {
            System.out.println("Found a ladder, climbing up " + curPosition + " -> " + finalPosition + "\n");
        } else if (finalPosition < nextPosition) {
            System.out.println("Bitten by a snake, going down from " + curPosition + " -> " + finalPosition + "\n");
        } else {
            System.out.println("Moved from " + curPosition + " -> " + finalPosition + "\n");
        }

        player.setCurrentPos(finalPosition);

        if (roll == 6) {
            System.out.println("another turn");
            takeTurn(player);
        }
    }

    public void play() {
        if (players.size() < 2) {
            System.out.println("Cannot start a game with just " + players.size() + " player");
            return;
        }

        this.status = GameStatus.RUNNING;
        System.out.println("Game Started");

        while(status == GameStatus.RUNNING) {
            Player curPlayer = players.poll();
            takeTurn(curPlayer);

            if (status == GameStatus.RUNNING) {
                players.add(curPlayer);
            }
        }

        System.out.println("Game finished");
        if (winner != null) {
            System.out.println("The winner is " + winner.getName());
        }
    }

    public static void main(String[] args) {
        List<BoardEntity> boardEntities = List.of(
                new Snake(17, 7), new Snake(54, 34),
                new Snake(62, 19), new Snake(98, 79),
                new Ladder(3, 38), new Ladder(24, 33),
                new Ladder(42, 93), new Ladder(72, 84)
        );

        List<String> players = Arrays.asList("Alice", "Bob", "Charlie");

        Game game = new GameBuilder()
                .setBoard(100, boardEntities)
                .setPlayers(players)
                .setDice(new Dice(1, 6))
                .build();

        game.play();
    }
}

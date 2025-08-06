package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import board.Board;
import dice.Dice;
import jumper.Jumper;
import player.Player;

public class Game {
    private static volatile Game instance;
    private Jumper[] jumpers;
    private Board board;
    private Dice dice;
    private Player[] players;
    private Queue<Player> playerQueue;

    private Game(Jumper[] jumpers, int boardSize, Player[] players, int sides) {
        this.board = new Board(boardSize);
        this.dice = new Dice(sides);
        this.jumpers = jumpers;
        this.players = players;
        this.playerQueue = new LinkedList<>(Arrays.asList(players));
    }

    public static Game getGameInstance(Jumper[] jumpers, int boardSize, Player[] players, int sides) {
        if (instance == null) {
            synchronized (Game.class) {
                if (instance == null) {
                    instance = new Game(jumpers, boardSize, players, sides);
                }
            }
        }
        return instance;
    }

    public void startGame(){
        while(true) {
            Player currentPlayer = playerQueue.poll();

            int roll = dice.roll();
            System.out.println("Player " + currentPlayer.getName() + " rolled a " + roll);

            if (board.isValidMove(currentPlayer.getCurrentPosition(), roll)) {
                int newPosition = currentPlayer.getCurrentPosition() + roll;

                for (Jumper jumper : jumpers) {
                    if (jumper.getStartPosition() == newPosition) {
                        newPosition = jumper.getEndPosition();
                        System.out.println("Player " + currentPlayer.getName() + " jumped from " + jumper.getStartPosition() + " to " + jumper.getEndPosition());
                        break;
                    }
                }

                if (board.checkWinner(newPosition)) {
                    System.out.println("Player " + currentPlayer.getName() + " wins!");
                    return;
                }

                currentPlayer.setCurrentPosition(newPosition);
            } 

            System.out.println("Current Player : " + currentPlayer.getName() + " at position " + currentPlayer.getCurrentPosition());
            playerQueue.offer(currentPlayer);
        }
    }

    public void getPlayers() {
        for (Player player : players) {
            System.out.println("Player: " + player.getName() + ", Position: " + player.getCurrentPosition());
        }
    }
}

import player.Player;

import shape.Shape;
import tictactoe.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Arghya", Shape.X);
        Player player2 = new Player("Ayush", Shape.O);

        TicTacToe game = TicTacToe.getTicTacToeInstance(3, new Player[] { player1, player2 });

        try {
            // simulate draw
            game.playMove(0, 0, player1); // X
            game.playMove(0, 1, player2); // O
            game.playMove(0, 2, player1); // X

            game.playMove(1, 1, player2); // O
            game.playMove(1, 0, player1); // X
            game.playMove(1, 2, player2); // O

            game.playMove(2, 1, player1); // X
            game.playMove(2, 0, player2); // O
            game.playMove(2, 2, player1); // X

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

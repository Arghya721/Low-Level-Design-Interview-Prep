import game.Game;
import jumper.Jumper;
import jumper.JumperFactory;
import player.Player;

public class Main {
    public static void main(String[] args) {
        // Create players

        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player[] players = {player1, player2};

        // Jumper and board setup would go here
        Jumper[] jumpers = {
            JumperFactory.createJumper("snake", 14, 7), // Snake from 14 to 7
            JumperFactory.createJumper("snake", 22, 3), // Snake from 22 to 3
            JumperFactory.createJumper("snake", 99, 1), // Snake from 99 to 1
            JumperFactory.createJumper("snake", 30, 11), // Snake from 30 to 11
            JumperFactory.createJumper("ladder", 2, 12), // Ladder from 2 to 12
            JumperFactory.createJumper("ladder", 8, 18), // Ladder from 8 to 18
            JumperFactory.createJumper("ladder", 4, 16)  // Ladder from 4 to 16
        };

        Game game = Game.getGameInstance(jumpers, 100, players, 6);
        game.startGame();
    }
}


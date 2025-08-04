package tictactoe;

import board.Board;
import player.Player;
import java.util.Stack;

public class TicTacToe {
    private static volatile TicTacToe instance;
    private Board board;
    private Player[] players;
    private Stack<Player> moves;
    private GameState gameState;

    private TicTacToe(int size, Player[] players){
        this.board = new Board(size);
        this.players = new Player[2];
        this.players = players;
        gameState = GameState.IN_PROGRESS;
        moves = new Stack<>();
    }

    public static synchronized TicTacToe getTicTacToeInstance(int size, Player[] players) {
        if(instance == null){
            instance = new TicTacToe(size, players);
        }

        return instance;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void playMove(int x, int y, Player player) throws Exception{
        if(gameState != GameState.IN_PROGRESS){
            throw new Exception("Game is already over");
        }

        if(board.isValidMove(x, y, player.getShape())){
            if(moves.isEmpty() || moves.peek() != player){
                board.playMove(x, y, player.getShape());
                moves.push(player);
                board.printBoard();
                // check for winning conditions
                if(board.isWinner(player.getShape())){
                    gameState = GameState.WIN;
                    System.out.println("Player " + player.getName() + " is the winner !!!");
                    return;
                }

                // check for draw condition
                if(board.isDraw(moves.size())){
                    gameState = GameState.DRAW;
                    System.out.println("DRAW");
                    return;
                }
            } else {
                throw new Exception("Wrong Player move");
            }
        } else {
            throw new Exception("Invalid Move");
        }
    }
    
}

package board;

public class Board {
    private int size;

    public Board(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isValidMove(int playerPosition, int roll)  {
        if(playerPosition == 0 && roll != 1){
            return false;
        }

        int newPosition = playerPosition + roll;
        return newPosition <= size;
    }

    public boolean checkWinner(int playerPosition) {
        return playerPosition == size;
    }
}

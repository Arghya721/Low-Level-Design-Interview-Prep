package player;

public class Player {
    private String name;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0; // Starting position
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}

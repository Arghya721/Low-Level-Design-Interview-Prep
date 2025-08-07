package jumper;

public class SnakeJumper implements Jumper {
    private int startPosition;
    private int endPosition;

    public SnakeJumper(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public int getStartPosition() {
        return startPosition;
    }

    @Override
    public int getEndPosition() {
        return endPosition;
    }
}

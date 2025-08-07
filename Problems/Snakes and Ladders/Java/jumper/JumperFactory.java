package jumper;

public class JumperFactory {
    public static Jumper createJumper(String jumper, int startPosition, int endPosition) {
        switch (jumper) {
            case "ladder":
                return new LadderJumper(startPosition, endPosition);
            case "snake":
                return new SnakeJumper(startPosition, endPosition);
            default:
                throw new IllegalArgumentException("Unknown jumper type: " + jumper);
        }
    }
}


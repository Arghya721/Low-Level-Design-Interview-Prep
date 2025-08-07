package dice;

import java.util.Random;

public class Dice {
    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        Random random = new Random();
        return random.nextInt(sides) + 1;
    }
}
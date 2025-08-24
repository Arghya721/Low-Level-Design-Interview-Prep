package payments;

public enum Note {
    TEN(10),    
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500);

    private final int value;
    
    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package war_game;

public class Card {
    public static final char SPADE = 6;
    public static final char HEART = 3;
    public static final char DIAMOND = 4;
    public static final char CLUB = 5;

    int value;
    String stringValue;
    char shape;

    public Card(int value, char shape) {
        this.value = value;
        stringValue = converToStringValue(value);
        this.shape = shape;
    }

    public void printCard() {
        System.out.printf("( %s %c ) ", stringValue, shape);
    }

    private String converToStringValue(int v) {
        if (v >= 2 && v <= 9) {
            return String.valueOf(Character.toChars(v + '0'));
        }
        if (v == 1) {
            return "A";
        }
        if (v == 10) {
            return "10";
        }
        if (v == 11) {
            return "J";
        }
        if (v == 12) {
            return "Q";
        }
        if (v == 13) {
            return "K";
        }
        return "error not in range (1-13)";
    }
}

package src;

public class Card {
    private final String value;
    private final String color;

    public static final String[] VALUE_ORDER = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi", "As"};
    public static final String[] COLORS = {"Trèfle", "Pique", "Cœur", "Carreau"};


    public Card(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }
    
    public int compareTo(Card otherCard) {
        int thisValueIndex = getValueIndex(this.value);
        int otherValueIndex = getValueIndex(otherCard.value);

        return Integer.compare(thisValueIndex, otherValueIndex);
    }

    private int getValueIndex(String value) {
        for (int i = 0; i < VALUE_ORDER.length; i++) {
            if (VALUE_ORDER[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return value + " de " + color;
    }
}

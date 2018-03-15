package Core;

public class Card {
    public int value;
    public Suit suit;
    public int points;

    public Card(int value, Suit suit, int points) {
        this.value = value;
        this.suit = suit;
        this.points = points;
    }

    public String toString() {
        return value + " of " + suit.toString();
    }

    public enum Suit {
        CLUBS, SPADES, DIAMONDS, HEARTS;

        private static final Suit[] suits = values();

        public static Suit[] getSuits() {
            return suits;
        }
    }
}

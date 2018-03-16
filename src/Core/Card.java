package Core;

public class Card implements Comparable<Card> {
    public int value;
    public Suit suit;
    public int points;

    public Card(int value, Suit suit, int points) {
        this.value = value;
        this.suit = suit;
        this.points = points;
    }

    public String toString() {
        if (suit != null) {
            return value + " of " + suit.toString();
        }
        if (value == 15) {
            return "SMALL JOKER";
        }
        return "LARGE JOKER";
    }

    @Override
    public int compareTo(Card card) {
        if (this.suit == card.suit) {
            return this.value - card.value;
        } else if (this.value == 15 || this.value == 16) {
            return 1;
        } else if (card.value == 15 || card.value == 16) {
            return -1;
        }
        else if (this.suit == Suit.SPADES) {
            return -1;
        } else if (this.suit == Suit.DIAMONDS) {
            return 1;
        } else if (card.suit == Suit.SPADES) {
            return 1;
        } else if (card.suit == Suit.DIAMONDS) {
            return -1;
        } else if (this.suit == Suit.HEARTS) {
            return -1;
        } else {
            return 1;
        }
    }

    public enum Suit {
        CLUBS, SPADES, DIAMONDS, HEARTS;

        private static final Suit[] suits = values();

        public static Suit[] getSuits() {
            return suits;
        }
    }
}

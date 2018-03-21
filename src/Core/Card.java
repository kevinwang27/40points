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
        if (value == 15) {
            return "SMALL JOKER";
        } else if (value == 16) {
            return "LARGE JOKER";
        }
        else {
            String name;
            switch (value) {
                case 11:
                    name = "J";
                    break;
                case 12:
                    name = "Q";
                    break;
                case 13:
                    name = "K";
                    break;
                case 14:
                    name = "A";
                    break;
                default:
                    name = value + "";
                    break;
            }
            return name + " of " + suit.toString();
        }

    }

    @Override
    public int compareTo(Card card) {
        if (this.suit == card.suit) {
            return this.value - card.value;
        } else if (this.value >= 15) {
            return 1;
        } else if (card.value >= 15) {
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

    public boolean isTrump(Suit trumpSuit, int trumpTier) {
        return (value >= 15 || value == trumpTier || suit == trumpSuit);
    }

    public enum Suit {
        CLUBS, SPADES, DIAMONDS, HEARTS, TRUMP;

        private static final Suit[] suits = new Suit[]{CLUBS, SPADES, DIAMONDS, HEARTS};

        public static Suit[] getSuits() {
            return suits;
        }
    }
}

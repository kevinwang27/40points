package Core;

import Core.Card;
import java.util.ArrayList;

public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        for (Card.Suit s : Card.Suit.getSuits()) {
            fillDeck(s);
        }
    }

    /* fills the deck with a particular suit of cards */
    private void fillDeck(Card.Suit suit) {
        for (int value = 2; value < 15; value++) {
            Card card = new Card(value, suit, 0);
            switch (value) {
                case 5:
                    card.points = 5;
                    break;
                case 10:
                case 13:
                    card.points = 10;
                    break;
            }
            cards.add(card);
        }
    }

    public boolean sixLeft() {
        return cards.size() <= 6;
    }

    public void displayLastSix() {
        assert sixLeft();
        System.out.println("Bottom six cards are: " + cards);
    }

    public Card.Suit decideTrumpFromLastSix(int trumpTier) {
        assert sixLeft();
        Card maxCard = cards.get(0);
        for (Card card : cards) {
            if (card.value == trumpTier) {
                return card.suit;
            }
            if (card.value > maxCard.value) {
                maxCard = card;
            }
        }
        return maxCard.suit;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

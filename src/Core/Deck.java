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

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

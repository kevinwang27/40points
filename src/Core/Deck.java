package Core;

import Core.Card;
import java.util.ArrayList;
import java.util.Comparator;

public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        for (Card.Suit s : Card.Suit.getSuits()) {
            fillDeck(s);
        }
        cards.add(new Card(15, null, 0));
        cards.add(new Card(16, null, 0));
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
        for (int i = 1; maxCard.value == 16 || maxCard.value == 15; i++) {
            maxCard = cards.get(i);
        }
        for (Card card : cards) {
            if (card.value == trumpTier) {
                return card.suit;
            }
            if (card.value > maxCard.value && card.value != 16 && card.value != 15) {
                maxCard = card;
            }
        }
        return maxCard.suit;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

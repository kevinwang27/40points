package Core;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    public ArrayList<Card> hand = new ArrayList<>();

    /* Draws a card from the initial pile and removes it from the deck
     * If deck has six cards left, throw SixLeftException */
    public void drawCard(Deck deck) throws SixLeftException {
        if (deck.sixLeft()) {
            throw new SixLeftException();
        }
        int index = (new Random()).nextInt(deck.cards.size());
        Card card = deck.cards.remove(index);
        hand.add(card);
    }

    public void drawLastSix(Deck deck) {
        hand.addAll(deck.cards);
        deck.cards.clear();
    }

    public class SixLeftException extends Exception {
    }
}

package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    public ArrayList<Card> hand = new ArrayList<>();

    /* Draws a card from the initial pile and removes it from the deck
     * If deck has six cards left, return null */
    public Card drawCard(Deck deck) {
        if (deck.sixLeft()) {
            return null;
        }
        int index = (new Random()).nextInt(deck.cards.size());
        Card card = deck.cards.remove(index);
        hand.add(card);
        sortHand();
        return card;
    }

    /* draw the last six cards and display the new hand */
    public void drawLastSix(Deck deck) {
        hand.addAll(deck.cards);
        deck.cards.clear();
        sortHand();
        System.out.println(hand);
    }

    /* sort the player's hand based on suit and value */
    public void sortHand() {
        Collections.sort(hand);
    }

    /* play the card at the given index */
    public Card playCard(int index) {
        Card card = hand.remove(index);
        System.out.println(card.toString());
        return card;
    }
}

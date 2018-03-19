package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Player {
    public ArrayList<Card> hand;
    private HashMap<Card.Suit, Integer> handSuits;
    private int playerNum;

    public Player(int playerNum) {
        this.playerNum = playerNum;
        hand  = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.getSuits()) {
            handSuits.put(suit, 0);
        }
        handSuits.put(Card.Suit.TRUMP, 0);
    }

    /* Draws a card from the initial pile and removes it from the deck
     * If deck has six cards left, return null */
    public Card drawCard(Deck deck, Card.Suit trumpSuit, int trumpTier) {
        if (deck.sixLeft()) {
            return null;
        }
        int index = (new Random()).nextInt(deck.cards.size());
        Card card = deck.cards.remove(index);
        hand.add(card);
        changeHandSuits(1, card, trumpSuit, trumpTier);
        sortHand();
        return card;
    }

    /* draw the last six cards and display the new hand */
    public void drawLastSix(Deck deck, Card.Suit trumpSuit, int trumpTier) { // update handSuits
        for (Card card : deck.cards) {
            hand.add(card);
            changeHandSuits(1, card, trumpSuit, trumpTier);
        }
        deck.cards.clear();
        sortHand();
        System.out.println("Drawing last six cards..");
    }

    /* sort the player's hand based on suit and value */
    public void sortHand() {
        Collections.sort(hand);
    }

    /* play the card at the given index */
    public Card playCard(int index, Card.Suit trumpSuit, int trumpTier) {
        Card card = hand.remove(index);
        changeHandSuits(-1, card, trumpSuit, trumpTier);
        System.out.println("Player " + playerNum + ": " + card.toString());
        return card;
    }

    /* add or subtract to handSuits */
    private void changeHandSuits(int i, Card card, Card.Suit trumpSuit, int trumpTier) {
        handSuits.put(card.suit, handSuits.get(card.suit) + i);
        if (card.suit == trumpSuit || card.value == trumpTier || card.value >= 15) {
            handSuits.put(Card.Suit.TRUMP, handSuits.get(Card.Suit.TRUMP) + i);
        }
    }

    /* get handSuits */
    public HashMap<Card.Suit, Integer> getHandSuits() {
        return handSuits;
    }

    /* get which player this is */
    public int getPlayerNum() {
        return playerNum;
    }
}

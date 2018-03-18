package Main;

import Core.Card;
import Core.Deck;
import Core.Pair;
import Core.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tier {
    private Deck deck;
    private Pair firstPair;
    private Pair secondPair;
    private int trumpTier;
    private Card.Suit trumpSuit;
    private Card[] pile;
    private Scanner reader; // close once im done

    public Tier(Pair firstPair, Pair secondPair) {
        deck = new Deck();
        this.trumpTier = firstPair.tier;
        this.firstPair = firstPair;
        this.secondPair = secondPair;
        reader = new Scanner(System.in);
    }

    /* play a single tier and return the winning pair */
    public Pair playTier() {
        drawCards();
        printPlayerOneHand();
        // play the round
        clearHands();
        return firstPair;
    }

    private void clearHands() {
        for (int i = 0; i < firstPair.players.length; i++) {
            firstPair.players[i].hand.clear();
            secondPair.players[i].hand.clear();

        }
    }

    /* draw cards */
    private void drawCards() {
        drawUntilSix();
        firstPair.players[0].drawLastSix(deck);
        playerOneChooseSixPile();
    }

    /* Players take turn drawing until six cards are left. */
    private void drawUntilSix() {
        boolean suitPicked = false;
        while (!deck.sixLeft()) {
            suitPicked = promptPlayerOneDraw(suitPicked);
            secondPair.players[0].drawCard(deck);
            firstPair.players[1].drawCard(deck);
            secondPair.players[1].drawCard(deck);
        }
        if (trumpSuit == null) {
            System.out.println("No one called");
            deck.displayLastSix();
            trumpSuit = deck.decideTrumpFromLastSix(trumpTier);
        }
        System.out.println("Trump suit is: " + trumpSuit);
    }

    /* Prompt player one to draw and return whether a trump suit was picked */
    private boolean promptPlayerOneDraw(boolean suitPicked) {
        System.out.println("Press enter to draw");
        while (true) {
            if (reader.nextLine().equals("")) {
                Card card = firstPair.players[0].drawCard(deck);
                System.out.println(firstPair.players[0].hand);
                if (card.value == trumpTier && !suitPicked) {
                    System.out.println("Type call to call or press enter instead");
                    if (reader.nextLine().equals("call")) {
                        trumpSuit = card.suit;
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    /* Player one chooses the six cards to set aside
     * Returns the pile of 6 */
    private void playerOneChooseSixPile() {
        pile = new Card[6];
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter indexes of cards you want to put in pile");
        String[] numbers = reader.nextLine().split(" ");
        while (numbers.length != 6) {
            System.out.println("Please enter *6* numbers");
            numbers = reader.nextLine().split(" ");
        }
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            ints.add(Integer.valueOf(numbers[i]));
        }
        ints.sort(Collections.reverseOrder());
        for (int i = 0; i < ints.size(); i++) {
            pile[i] = firstPair.players[0].hand.remove((int) ints.get(i));
        }
    }

    /* prints player one's hand */
    private void printPlayerOneHand() {
        System.out.println(firstPair.players[0].hand);
    }

    private void playRound(Pair firstPair, Pair secondPair, int trumpTier, Card.Suit trumpSuit) {
        ArrayList<Card> roundCards = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        System.out.println("Which card do you want to play?");
        roundCards.add(firstPair.players[0].playCard(reader.nextInt()));
        reader.close();
    }

}

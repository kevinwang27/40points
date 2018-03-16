package Main;

import Core.Card;
import Core.Deck;
import Core.Pair;
import Core.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    public Pair pair1_3;
    public Pair pair2_4;

    public Game(Pair pair1_3, Pair pair2_4) {
        this.pair1_3 = pair1_3;
        this.pair2_4 = pair2_4;
    }

    private boolean gameOver() {
        return pair1_3.tier > 14 || pair2_4.tier > 14;
    }

    /* default start w/ Players 1 and 3 as the first pair */
    public void start() {
        Pair firstPair = playTier(pair1_3, pair2_4);
        /*while (!gameOver()) {
            if (firstPair == pair1_3) {
                firstPair = playTier(pair1_3, pair2_4);
            } else {
                firstPair = playTier(pair2_4, pair1_3);
            }
        }*/
    }

    public Pair playTier(Pair firstPair, Pair secondPair) {
        Deck deck = new Deck();
        int trumpTier = firstPair.tier;
        Card.Suit trumpSuit = drawUntilSix(firstPair, secondPair, deck, trumpTier);
        System.out.println("Trump suit is: " + trumpSuit);

        firstPair.players[0].drawLastSix(deck);
        printHand(firstPair.players[0]);

        Card[] pile = chooseSixPile(firstPair.players[0]);
        printHand(firstPair.players[0]);

        //Pair winner = play(firstPair, secondPair, trumpTier, trumpSuit, pile);
        return firstPair;
    }

    private Pair play(Pair firstPair, Pair secondPair, int trumpTier, Card.Suit trumpSuit, Card[] pile) {
        while (firstPair.players[0].hand.size() > 0) {
            playRound(firstPair, secondPair, trumpTier, trumpSuit);
        }
        if (secondPair.points >= 40) {
            return secondPair;
        }
        return firstPair;
    }

    private void playRound(Pair firstPair, Pair secondPair, int trumpTier, Card.Suit trumpSuit) {
        ArrayList<Card> roundCards = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        System.out.println("Which card do you want to play?");
        roundCards.add(firstPair.players[0].playCard(reader.nextInt()));
        reader.close();
    }

    /* Players take turn drawing until six cards are left.
     * Returns the trump Suit for the game */
    private Card.Suit drawUntilSix(Pair firstPair, Pair secondPair, Deck deck, int trumpTier) {
        Card.Suit trumpSuit = null;
        boolean suitPicked = false;
        Scanner reader = new Scanner(System.in);
        while (!deck.sixLeft()) {
            System.out.println("Press enter to draw");
            if (reader.nextLine().equals("")) {
                Card card = firstPair.players[0].drawCard(deck);
                printHand(firstPair.players[0]);
                if (card.value == trumpTier && !suitPicked) {
                    System.out.println("Press 2 to call or another number to pass");
                    if (reader.nextInt() == 2) {
                        trumpSuit = card.suit;
                        suitPicked = true;
                    } else {
                        continue;
                    }
                }
            }
            secondPair.players[0].drawCard(deck);
            firstPair.players[1].drawCard(deck);
            secondPair.players[1].drawCard(deck);
        }
        if (trumpSuit == null) {
            System.out.println("No one called");
            deck.displayLastSix();
            trumpSuit = deck.decideTrumpFromLastSix(trumpTier);
        }
        reader.close();
        return trumpSuit;
    }

    private Card[] chooseSixPile(Player player) {
        Card[] pile = new Card[6];
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
            pile[i] = player.hand.remove((int) ints.get(i));
        }
        return pile;
    }

    private void printHand(Player player) {
        System.out.println(player.hand);
    }
}

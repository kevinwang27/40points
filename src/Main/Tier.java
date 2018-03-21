package Main;

import Core.Card;
import Core.Deck;
import Core.Pair;
import Core.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tier {
    private int indexOfFirstPlayer;
    private int trumpTier;
    private Deck deck;
    private Pair firstPair;
    private Pair secondPair;
    private ArrayList<Player> orderOfPlayers;
    private Card.Suit trumpSuit;
    private Card[] pile;
    private Scanner reader;

    Tier(Pair firstPair, Pair secondPair, int indexOfFirstPlayer) {
        deck = new Deck();
        this.trumpTier = firstPair.tier;
        this.firstPair = firstPair;
        this.secondPair = secondPair;
        pile = new Card[6];
        reader = new Scanner(System.in);
        this.indexOfFirstPlayer = indexOfFirstPlayer;
        this.orderOfPlayers = new ArrayList<>();
        setOrderOfPlayers();
        clearHands();
        clearPoints();
    }

    /* play a single tier and return the winning pair */
    public Pair playTier() {
        drawCards();
        setHandSuits();
        return playRounds();
    }

    private void setHandSuits() {
        assert trumpSuit != null;
        for (Player player : orderOfPlayers) {
            player.setHandSuits(trumpSuit, trumpTier);
        }
    }

    /* play the rounds of the tier */
    private Pair playRounds() {
        Pair winnerPair = firstPair;
        Pair loserPair = secondPair;
        while (!tierOver()) {
            System.out.println("Next Round:\n");
            Round round = new Round(winnerPair, loserPair, trumpTier, trumpSuit, orderOfPlayers);
            winnerPair = round.playRound(reader);
            orderOfPlayers = round.getOrderOfPlayers();
            printPlayerOneHand();
            if (winnerPair == firstPair) {
                loserPair = secondPair;
            } else {
                loserPair = firstPair;
            }
        }
        winnerPair.points += getPilePoints();
        if (secondPair.points >= 40) {
            return secondPair;
        }
        return firstPair;
    }

    /* clear all players' hands to prep for next tier */
    private void clearHands() {
        for (int i = 0; i < firstPair.players.length; i++) {
            firstPair.players[i].hand.clear();
            secondPair.players[i].hand.clear();
        }
    }

    /* clear both pairs' points */
    private void clearPoints() {
        firstPair.points = 0;
        secondPair.points = 0;
    }

    /* draw cards */
    private void drawCards() {
        Player firstPlayer = firstPair.players[indexOfFirstPlayer];
        drawUntilSix();
        firstPlayer.drawLastSix(deck);
        if (firstPlayer.getPlayerNum() == 1) {
            printPlayerOneHand();
            playerOneChooseSixPile();
        } else {
            compChooseSixPile(firstPlayer);
        }
        printPlayerOneHand();
    }

    /* Players take turn drawing until six cards are left. */
    private void drawUntilSix() {
        boolean suitPicked = false;
        while (!deck.sixLeft()) {
            suitPicked = drawRound(suitPicked);
        }
        if (trumpSuit == null) {
            System.out.println("No one called");
            deck.displayLastSix();
            trumpSuit = deck.decideTrumpFromLastSix(trumpTier);
        }
        System.out.println("Trump suit is: " + trumpSuit);
    }

    /* one round of card drawing */
    private boolean drawRound(boolean suitPicked) {
        for (Player player : orderOfPlayers) {
            if (player.getPlayerNum() == 1) {
                suitPicked = promptPlayerOneDraw(suitPicked);
            } else {
                player.drawCard(deck);
            }
        }
        return suitPicked;
    }

    /* Prompt player one to draw and return whether a trump suit was picked */
    private boolean promptPlayerOneDraw(boolean suitPicked) {
        System.out.println("Press enter to draw");
        while (true) {
            if (reader.nextLine().equals("")) {
                Card card = firstPair.players[0].drawCard(deck);
                System.out.println("Card drawn: " + card.toString());
                printPlayerOneHand();
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
        return suitPicked;
    }

    /* Player one chooses the six cards to set aside
     * Returns the pile of 6 */
    private void playerOneChooseSixPile() {
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

    /* Given computer player selects the six cars to set aside */
    private void compChooseSixPile(Player player) {
        for (int i = 0; i < pile.length; i++) {
            pile[i] = player.hand.remove(i);
        }
    }

    /* prints player one's hand */
    private void printPlayerOneHand() {
        System.out.println("Hand: " + firstPair.players[0].hand);
    }

    /* test if the tier is completed */
    private boolean tierOver() {
        return firstPair.players[0].hand.isEmpty() &&
                secondPair.players[0].hand.isEmpty() &&
                firstPair.players[1].hand.isEmpty() &&
                secondPair.players[1].hand.isEmpty();
    }

    /* set the order of the players based on indexOfFirstPlayer */
    private void setOrderOfPlayers() {
        orderOfPlayers.add(firstPair.players[indexOfFirstPlayer]);
        orderOfPlayers.add(secondPair.players[indexOfFirstPlayer]);
        orderOfPlayers.add(firstPair.players[1 - indexOfFirstPlayer]);
        orderOfPlayers.add(secondPair.players[1 - indexOfFirstPlayer]);
    }

    public int getPilePoints() {
        int points = 0;
        for (Card card : pile) {
            points += card.points;
        }
        return points * 2;
    }

}

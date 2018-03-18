package Main;

import Core.Card;
import Core.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class Round { // make game more user friendly (display info), evaluate winner based on suit, restrict comp play
    private Pair firstPair;
    private Pair secondPair;
    private int trumpTier;
    private Card.Suit trumpSuit;
    private ArrayList<Card> roundCards;
    private Pair pairWithPlayerOne;

    public Round(Pair firstPair, Pair secondPair, int trumpTier, Card.Suit trumpSuit, Pair pairWithPlayerOne) {
        this.firstPair = firstPair;
        this.secondPair = secondPair;
        this.trumpTier = trumpTier;
        this.trumpSuit = trumpSuit;
        roundCards = new ArrayList<>();
        this.pairWithPlayerOne = pairWithPlayerOne;
    }

    /* all players play a card in order
     * add the points to the winning pair and return the winner */
    public Pair playRound(Scanner reader) {
        if (firstPair == pairWithPlayerOne) {
            roundCards.add(promptPlayerOnePlay(reader));
            roundCards.add(secondPair.players[0].playCard(0));
            roundCards.add(firstPair.players[1].playCard(0));
            roundCards.add(secondPair.players[1].playCard(0));
            Pair winnerPair = evaluateRoundWinner(roundCards, firstPair);
            winnerPair.addPoints(evaluateRoundPoints(roundCards));
            return winnerPair;
        } else {
            roundCards.add(firstPair.players[0].playCard(0));
            roundCards.add(promptPlayerOnePlay(reader));
            roundCards.add(firstPair.players[1].playCard(0));
            roundCards.add(secondPair.players[1].playCard(0));
            Pair winnerPair = evaluateRoundWinner(roundCards, secondPair);
            winnerPair.addPoints(evaluateRoundPoints(roundCards));
            return winnerPair;
        }
    }

    /* evaluate how many points the round was worth */
    private int evaluateRoundPoints(ArrayList<Card> roundCards) {
        int roundPoints = 0;
        for (Card card : roundCards) {
            int value = card.value;
            if (value == 5) {
                roundPoints += 5;
            } else if (value == 10 || value == 13) {
                roundPoints += 10;
            }
        }
        return roundPoints;
    }

    /* evaluate who won the round based on suit, value, etc */
    private Pair evaluateRoundWinner(ArrayList<Card> roundCards, Pair pairWithPlayerOne) {
        int index = indexOfWinner(roundCards);
        if (firstPair == pairWithPlayerOne) {
            if (index == 0 || index == 2) {
                return firstPair;
            }
            return secondPair;
        } else {
            if (index == 1 || index == 3) {
                return firstPair;
            }
            return secondPair;
        }
    }

    /* return the index of the winning card in roundCards */
    private int indexOfWinner(ArrayList<Card> roundCards) {
        int maxIndex = 0;
        for (int i = 0; i < roundCards.size(); i++) {
            if (roundCards.get(i).value > roundCards.get(maxIndex).value) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /* Prompt player one to play and return the card played */
    private Card promptPlayerOnePlay(Scanner reader) {
        System.out.println("Enter index of card to play");
        int index = reader.nextInt();
        while (index < 0 || index >= pairWithPlayerOne.players[0].hand.size()) {
            System.out.println("Please enter a valid index");
            index = reader.nextInt();
        }
        Card card = pairWithPlayerOne.players[0].playCard(index);
        return card;
    }
}

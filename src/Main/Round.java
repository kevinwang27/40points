package Main;

import Core.Card;
import Core.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class Round { // restrict cards able to be played, computer calls trumpsuit, player starting order
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
        } else {
            roundCards.add(firstPair.players[0].playCard(0));
            roundCards.add(promptPlayerOnePlay(reader));
            roundCards.add(firstPair.players[1].playCard(0));
            roundCards.add(secondPair.players[1].playCard(0));
        }
        Pair winnerPair = evaluateRoundWinner(roundCards);
        winnerPair.addPoints(evaluateRoundPoints(roundCards));
        return winnerPair;
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
    private Pair evaluateRoundWinner(ArrayList<Card> roundCards) {
        int index = indexOfWinner(roundCards);
        if (index == 0 || index == 2) {
            return firstPair;
        }
        return secondPair;
    }

    /* return the index of the winning card in roundCards
     * based on rules of 40 points */
    private int indexOfWinner(ArrayList<Card> roundCards) {
        int maxIndex = 0;
        Card maxCard = roundCards.get(0);
        Card initialCard = roundCards.get(0);
        for (int i = 1; i < roundCards.size(); i++) {
            Card card = roundCards.get(i);
            /* playing trump suit */
            if (initialCard.suit == trumpSuit || initialCard.value == trumpTier || initialCard.value >= 15 ||
                    maxCard.suit == trumpSuit || maxCard.value == trumpTier || maxCard.value >= 15) {
                if (card.value > maxCard.value && maxCard.value != trumpTier && card.suit == trumpSuit) {
                    maxIndex = i;
                    maxCard = card;
                } else if (maxCard.value == trumpTier && (card.value >= 15 ||
                        (maxCard.suit != trumpSuit && card.suit == trumpSuit && card.value == trumpTier))) {
                    maxIndex = i;
                    maxCard = card;
                } else if (card.value == trumpTier && maxCard.value < 15 && maxCard.value != trumpTier) {
                    maxIndex = i;
                    maxCard = card;
                }
            } else {
                if (card.suit == trumpSuit || card.value >= 15 || card.value == trumpTier) {
                    maxIndex = i;
                    maxCard = card;
                } else if (card.value > maxCard.value && card.suit == initialCard.suit) {
                    maxIndex = i;
                    maxCard = card;
                }
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

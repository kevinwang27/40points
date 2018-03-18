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

    public void start() {
        Pair tierWinner = pair1_3;
        Pair tierLoser = pair2_4;
        while (!gameOver()) {
            Tier tier = new Tier(tierWinner, tierLoser);
            tierWinner = tier.playTier();
            if (tierWinner == pair1_3) {
                tierLoser = pair2_4;
            } else {
                tierLoser = pair1_3;
            }
            tierWinner.incrementTier();
        }
    }

    private boolean gameOver() {
        return pair1_3.tier > 14 || pair2_4.tier > 14;
    }




}

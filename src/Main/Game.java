package Main;

import Core.Pair;

public class Game {
    public Pair pair1_3;
    public Pair pair2_4;

    public Game(Pair pair1_3, Pair pair2_4) {
        this.pair1_3 = pair1_3;
        this.pair2_4 = pair2_4;
    }

    /* play the game */
    public void start() {
        Pair tierWinner = pair1_3;
        Pair tierLoser = pair2_4;
        while (!gameOver()) {
            System.out.println("New Game:\n");
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

    /* test if the game is over */
    private boolean gameOver() {
        return pair1_3.tier > 14 || pair2_4.tier > 14;
    }

}

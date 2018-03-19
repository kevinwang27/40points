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
        Pair lastWinner;
        Pair tierWinner = pair1_3;
        Pair tierLoser = pair2_4;
        int indexOfNextFirstPlayer = 0;
        while (!gameOver()) {
            System.out.println("New Game: \nCurrent tier: " + tierWinner.tier);
            Tier tier = new Tier(tierWinner, tierLoser, indexOfNextFirstPlayer);
            lastWinner = tierWinner;
            tierWinner = tier.playTier();
            printPoints();
            if (tierWinner == pair1_3) {
                System.out.println("Winner is Players 1 + 3");
                tierLoser = pair2_4;
            } else {
                System.out.println("Winner is Players 2 + 4");
                tierLoser = pair1_3;
            }
            if (tierWinner == lastWinner) {
                tierWinner.incrementTier();
                indexOfNextFirstPlayer = 1 - indexOfNextFirstPlayer;
            }
        }
    }

    /* print end-of-tier point standings */
    private void printPoints() {
        System.out.println("Points:\nPlayers 1 + 3: " + pair1_3.points + "\nPlayers 2 + 4: " + pair2_4.points);
    }

    /* test if the game is over */
    private boolean gameOver() {
        return pair1_3.tier > 14 || pair2_4.tier > 14;
    }

}

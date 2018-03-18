package Main;

import Core.Pair;
import Core.Player;

/*
 * Main class for running the game
 */
public class App {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        Game game = new Game(new Pair(player1, player3), new Pair(player2, player4));
        game.start();
    }


}

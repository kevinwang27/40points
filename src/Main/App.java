package Main;

import Core.Pair;
import Core.Player;

/*
 * Main class for running the game
 */
public class App {
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);

        Game game = new Game(new Pair(player1, player3), new Pair(player2, player4));
        game.start();
    }


}

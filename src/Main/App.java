package Main;

import Core.Pair;
import Core.Player;
import GUI.GamePanel;
import GUI.SuitImages;

import java.io.IOException;

/*
 * Main class for running the game
 */
public class App {
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);

        try {
            Game game = new Game(new Pair(player1, player3), new Pair(player2, player4));
            SuitImages.bufferImages();
            GamePanel gamePanel = new GamePanel(player1);
            gamePanel.display();
            game.start(gamePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package Game;

import Core.Deck;
import Core.Player;
import GUI.GamePanel;
import GUI.SuitImages;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            SuitImages.BufferImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Deck deck = new Deck();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        GamePanel gPanel = new GamePanel(player1);
        gPanel.display();

        try {
            while (true) {
                player1.drawCard(deck);
                gPanel.updateCards();
                player2.drawCard(deck);
                player3.drawCard(deck);
                player4.drawCard(deck);
            }
        } catch (Player.SixLeftException e) {
            player1.drawLastSix(deck);
            gPanel.updateCards();
        }
    }
}

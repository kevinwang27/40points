package Core;

public class Pair {
    public Player[] players;
    public int tier;

    public Pair(Player player1, Player player2) {
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        tier = 2;
    }
}

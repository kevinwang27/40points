package Core;

public class Pair {
    public Player[] players;
    public int tier;
    public int points;

    public Pair(Player player1, Player player2) {
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        tier = 2;
        points = 0;
    }

    /* increment the pair's tier when they win */
    public void incrementTier() {
        this.tier += 1;
    }

    /* add points to the pair */
    public void addPoints(int points) {
        this.points += points;
    }
}

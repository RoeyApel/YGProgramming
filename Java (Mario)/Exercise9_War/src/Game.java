import java.util.ArrayList;

public class Game {
    Deck sortedDeck;
    ArrayList<Player> players;

    public Game(int numOfPlayers) {
        sortedDeck = new Deck();
        sortedDeck.setSortedDeck();

        players = new ArrayList<>();

        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(sortedDeck.deck, 52 / numOfPlayers));
            players.get(i).playerDeck.print();
        }
    }
}

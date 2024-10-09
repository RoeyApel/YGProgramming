import java.util.ArrayList;

public class Game {
    Deck sortedDeck;
    Deck onTableDeck;
    ArrayList<Player> players;

    public Game() {
        sortedDeck = new Deck();
        onTableDeck = new Deck();
    }

    public void play(int numOfPlayers) {
        resetGame(numOfPlayers);

        GameStates gameState = GameStates.CONTINUE;

        while (gameState == GameStates.CONTINUE) {
            gameState = ExecuteTurn();
            if (gameState == GameStates.WAR) {
                for (Player player : players) {
                }
            }
        }
    }

    private GameStates ExecuteTurn() {
        Card card;
        ArrayList<Card> currentDeck;
        Player turnWinner;
        int max = Integer.MIN_VALUE;
        int equalityCount = 0;

        for (Player player : players) {
            currentDeck = player.playerDeck.deck;
            card = currentDeck.remove(currentDeck.size() - 1);
            onTableDeck.deck.add(card);

            if (card.value > max) {
                max = card.value;
                turnWinner = player;
            } else if (card.value == max) {
                equalityCount++;
            }
        }

        if (equalityCount == players.size() - 1) {

        }
    }

    private void resetGame(int numOfPlayers) {
        sortedDeck.setSortedDeck();
        players = new ArrayList<>();

        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(sortedDeck.deck, 52 / numOfPlayers));
        }
    }

}

enum GameStates {
    CONTINUE, WAR, END;
}

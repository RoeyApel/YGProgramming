public class BingoGame {
    BingoDeck managerDeck;
    BingoPlayer[] players;

    public BingoGame() {
        managerDeck = new BingoDeck();
        players = new BingoPlayer[100];
    }

    public void startGame() {
        managerDeck.mixDeck();

        BingoDeck bingoDeck = new BingoDeck();
        for (int i = 0; i < players.length; i++) {
            players[i] = new BingoPlayer(bingoDeck.deck);
            bingoDeck.mixDeck();
        }
    }

}

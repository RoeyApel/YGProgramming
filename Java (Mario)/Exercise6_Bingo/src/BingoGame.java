public class BingoGame {
    BingoDeck managerDeck;
    BingoPlayer[] players;

    public BingoGame() {
        managerDeck = new BingoDeck();
        players = new BingoPlayer[100];
    }

    public void play() {
        managerDeck.mixDeck();

        BingoDeck bingoDeck = new BingoDeck();
        for (int i = 0; i < players.length; i++) {
            players[i] = new BingoPlayer(bingoDeck.deck);
            bingoDeck.mixDeck();
        }

        for (int i = 0; i < 28; i++) {
            System.out.println("Card " + (i + 1) + ": [ " + managerDeck.deck[i] + " ]");
            for (int j = 0; j < players.length; j++) {
                if (!players[j].loser) {
                    players[j].checkIfLoser(i);
                }
            }
        }

        System.out.println("------------------------------------");

        for (int i = 0; i < players.length; i++) {
            if (!players[i].loser) {
                System.out.println(players[i].playerId + " Won!");
                players[i].printBoard();
            }
        }

        BingoPlayer.count = 0;
    }

}

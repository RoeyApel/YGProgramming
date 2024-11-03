public class BingoGame {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";

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
            players[i] = new BingoPlayer(bingoDeck.deck, i);
            bingoDeck.mixDeck();
        }

        for (int i = 0; i < 28; i++) {
            // System.out.print(managerDeck.deck[i] + ", ");

            System.out.println("Card " + (i + 1) + ": [ " + managerDeck.deck[i] + " ]");
            for (int j = 0; j < players.length; j++) {
                if (!players[j].loser) {
                    players[j].checkIfLoser(managerDeck.deck[i]);
                }
            }
        }

        System.out.println("------------------------------------");

        int countWinners = 0;
        for (int i = 0; i < players.length; i++) {
            if (!players[i].loser) {
                System.out.println(GREEN + players[i].playerId + " Won!");
                players[i].printBoard();
                System.out.print(RESET);
                countWinners++;
            }
        }
        if (countWinners == 0) {
            System.out.println(RED + "No winners this round!" + RESET);
        }

        System.out.println("------------------------------------");

    }

}

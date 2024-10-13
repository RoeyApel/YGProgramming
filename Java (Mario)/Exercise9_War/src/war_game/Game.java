package war_game;

import java.util.ArrayList;
import java.util.Scanner;
import static tools.Colors.*;

public class Game {
    Deck sortedDeck;
    Deck onTableDeck;
    ArrayList<Player> players;
    int numOfPlayers;

    private Scanner scanner;

    public Game() {
        numOfPlayers = 0;
        sortedDeck = new Deck();
        onTableDeck = new Deck();
        scanner = new Scanner(System.in);

    }

    public void play(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;

        resetGame();
        clearScreen();

        System.out.println(PURPLE.get() + "Welcome To Our Game - War!" + RESET.get());
        System.out.println("Press Enter To Start");
        scanner.nextLine();
        clearScreen();

        TurnStatus turnStatus = TurnStatus.CONTINUE;

        do {
            turnStatus = turn();
            while (turnStatus == TurnStatus.WAR) {
                turnStatus = turn();

            }
            if (turnStatus == TurnStatus.CONTINUE) {
                System.out.println("\nPress Enter To Continue");
                scanner.nextLine();
                clearScreen();
            }
        } while (turnStatus != TurnStatus.END);
    }

    private TurnStatus turn() {
        ArrayList<Integer> winnersIds = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        Card card;

        System.out.print(CYAN.get());

        for (Player player : players) {
            if (player.getStatus() == PlayerStatus.ACTIVE) {

                card = player.getDeck().takeTop();
                onTableDeck.putTop(card);

                if (player.outOfCards()) {
                    if (player.haveExtraCards()) {
                        player.putExtraInDeck();
                    } else {
                        player.setStatus(PlayerStatus.INACTIVE);
                        continue;
                    }
                }

                player.setStatus(PlayerStatus.TURN_LOST);

                System.out.print("Player " + player.id + ": ");
                card.printCard();

                if (card.value > max) {
                    max = card.value;
                    winnersIds.clear();
                    winnersIds.add(player.id);
                } else if (card.value == max) {
                    winnersIds.add(player.id);
                }
            }
        }
        System.out.println(RESET.get());

        if (winnersIds.size() > 1) {
            setWinnersActive(winnersIds);
            for (Player player : players) {
                if (player.getStatus() == PlayerStatus.ACTIVE) {
                    player.getDeck().flip(2);
                }
            }
            return TurnStatus.WAR;
        }

        setWinnerActive(winnersIds.get(0));
        Player winner = players.get(winnersIds.get(0) - 1);
        winner.getExtraDeck().putAll(onTableDeck);

        System.out.println();
        for (Player player : players) {
            printPlayer(player, winner);
        }

        if (winner.getDeck().size() + winner.getExtraDeck().size() == Deck.MAX_CAPACITY) {
            System.out.println(BLUE.get() + "\nThe Winner Is Player " + winner.id + RESET.get());
            return TurnStatus.END;
        }

        setAllActive();

        return TurnStatus.CONTINUE;
    }

    private void setWinnersActive(ArrayList<Integer> winnersIds) {
        for (int i = 0; i < winnersIds.size(); i++) {
            players.get(winnersIds.get(i) - 1).setStatus(PlayerStatus.ACTIVE);
        }
    }

    private void setWinnerActive(int winnerId) {
        players.get(winnerId - 1).setStatus(PlayerStatus.ACTIVE);
    }

    private void setAllActive() {
        for (Player player : players) {
            if (player.getStatus() == PlayerStatus.TURN_LOST) {
                player.setStatus(PlayerStatus.ACTIVE);
            }
        }
    }

    private void printPlayer(Player player, Player winner) {
        if (player.id == winner.id) {
            System.out.print(GREEN.get() + "Player " + player.id + " is Turn Winner ");
            System.out.println("and his deck's size is: " + player.getDeck().size() + "|" + player.getExtraDeck().size()
                    + RESET.get());

        } else if (player.getStatus() != PlayerStatus.INACTIVE) {
            System.out.print(RED.get() + "Player " + player.id + " is Turn Loser ");
            System.out.println("and his deck's size is: " + player.getDeck().size() + "|" + player.getExtraDeck().size()
                    + RESET.get());
        }
    }

    private void resetGame() {
        sortedDeck.setSortedDeck();

        players = new ArrayList<>();

        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(sortedDeck.deck, 52 / numOfPlayers, i + 1));
        }
    }

    private void clearScreen() {
        System.out.print("\33c");
    }

}

enum TurnStatus {
    CONTINUE, END, WAR;
}

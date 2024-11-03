package war_game;

import java.util.ArrayList;

public class Player {
    int id;
    private Deck playerDeck;
    private Deck playerExtraDeck;
    private PlayerStatus playerStatus;

    public Player(ArrayList<Card> sortedDeck, int numOfCards, int id) {
        this.id = id;
        playerStatus = PlayerStatus.ACTIVE;
        playerDeck = new Deck();
        playerExtraDeck = new Deck();

        for (int i = 0; i < numOfCards; i++) {
            int rndIndex = getRndNum(0, sortedDeck.size());
            Card addedCard = sortedDeck.remove(rndIndex);
            playerDeck.deck.add(addedCard);
        }
    }

    public boolean outOfCards() {
        return playerDeck.isEmpty();
    }

    public boolean haveExtraCards() {
        return !playerExtraDeck.isEmpty();
    }

    public void putExtraInDeck() {
        playerExtraDeck.shafleDeck();
        playerDeck.putAll(playerExtraDeck);
    }

    private int getRndNum(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public PlayerStatus getStatus() {
        return playerStatus;
    }

    public void setStatus(PlayerStatus status) {
        this.playerStatus = status;
    }

    public Deck getDeck() {
        return playerDeck;
    }

    public Deck getExtraDeck() {
        return playerExtraDeck;
    }
}

enum PlayerStatus {
    ACTIVE, INACTIVE, TURN_LOST;
}

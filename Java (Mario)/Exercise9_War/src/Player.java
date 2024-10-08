import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    Deck playerDeck;

    public Player(ArrayList<Card> sortedDeck, int numOfCards) {
        playerDeck = new Deck();

        for (int i = 0; i < numOfCards; i++) {
            int rndIndex = getRndNum(0, sortedDeck.size());
            Card addedCard = sortedDeck.remove(rndIndex);
            playerDeck.deck.add(addedCard);
        }
    }

    private int getRndNum(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}

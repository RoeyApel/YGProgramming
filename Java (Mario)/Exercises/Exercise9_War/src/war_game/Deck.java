package war_game;

import java.util.ArrayList;

public class Deck {
    public static final int MAX_CAPACITY = 52;
    ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>(52);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public int size() {
        return deck.size();
    }

    public void putTop(Card card) {
        deck.add(card);
    }

    public Card takeTop() {
        return deck.remove(deck.size() - 1);
    }

    public void putAllButtom(Deck otherDeck) {
        deck.addAll(0, otherDeck.deck);
        otherDeck.deck.clear();
    }

    public void putAll(Deck otherDeck) {
        deck.addAll(otherDeck.deck);
        otherDeck.deck.clear();
    }

    public void flip(int times) {
        if (deck.size() <= times) {
            return;
        }
        for (int i = 0; i < times; i++) {
            deck.add(0, deck.remove(deck.size() - 1));
        }
    }

    public void printDeck(int startIndex) {
        for (int i = startIndex; i < deck.size(); i++) {
            deck.get(i).printCard();
        }
        System.out.println();
    }

    public void setSortedDeck() {
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, Card.CLUB));
            deck.add(new Card(i, Card.SPADE));
            deck.add(new Card(i, Card.HEART));
            deck.add(new Card(i, Card.DIAMOND));
        }
    }

    public void shafleDeck() {
        int min = 0, max = deck.size() - 1;

        for (int i = 0; i < deck.size(); i++) {
            int rndIndex = (int) (Math.random() * (max - min) + min);

            deck.add(deck.remove(rndIndex));
        }
    }
}

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>(52);
    }

    public void print() {
        for (int i = 0; i < deck.size(); i++) {
            deck.get(i).print();
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
}

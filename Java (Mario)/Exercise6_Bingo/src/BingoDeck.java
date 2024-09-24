public class BingoDeck {
    int[] deck;

    public BingoDeck() {
        deck = new int[30];

        for (int i = 0; i < deck.length; i++) {
            deck[i] = i + 1;
        }
        mixDeck();
    }

    public void mixDeck() {
        int min = 0, max = 29;
        int temp = 0;

        for (int i = 0; i < deck.length; i++) {
            int rndIndex = (int) (Math.random() * (max - min) + min);

            temp = deck[i];
            deck[i] = deck[rndIndex];
            deck[rndIndex] = temp;
        }
    }

}

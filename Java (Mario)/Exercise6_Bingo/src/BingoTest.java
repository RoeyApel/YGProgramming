import java.util.Arrays;

public class BingoTest {
    public static void main(String[] args) throws Exception {
        BingoPlayer player = new BingoPlayer(new BingoDeck().deck);
        player.printBoard();
    }
}

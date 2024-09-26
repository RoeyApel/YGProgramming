import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Board b = new Board(4);
        b.printBoard();
        Game game = new Game();
        game.getKFromUser(scanner);
        scanner.close();
    }
}

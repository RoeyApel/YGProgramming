import java.util.Scanner;

public class Game {
    private final int CONTINUE = 0;
    private final int DRAW = 1;
    private final int WON = 2;
    Scanner scanner;
    Board board;
    int k;

    public Game() {
        scanner = new Scanner(System.in);
    }

    public void play() {
        int turn = 0;
        clearScreen();

        k = getIntUserInput("Type number k to play Kinarow: ");
        while (k < 4) {
            k = getIntUserInput("Number can't be less than 4. Try Again: ");
        }
        clearScreen();

        board = new Board(k);
        board.printBoard();

        do {

            if (turn % 2 == 0) {
                playTurn(board.PLAYER1, 1);
            } else {
                playTurn(board.PLAYER2, 2);
            }
            turn++;

        } while (findGameState() == CONTINUE);

        scanner.close();
    }

    private void playTurn(char player, int id) {
        int col = getIntUserInput("Player " + id + " place your piece in column number: ");

        while (board.place(col - 1, player) == false) {
            col = getIntUserInput("Invaild placment. Please try Again: ");
        }
        clearScreen();

        board.printBoard();
    }

    private int findGameState() {
        return CONTINUE;
    }

    private int getIntUserInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Error please type a number: ");
            scanner.nextLine();
        }

        int input = scanner.nextInt();
        return input;
    }

    private void clearScreen() {
        System.out.print("\33c");
    }
}

import java.util.Scanner;

public class Game {
    private final int CONTINUE = 0;
    private final int DRAW = 1;
    private final int PLAYER1_WON = 2;
    private final int PLAYER2_WON = 3;
    Scanner scanner;
    Board board;
    int k;

    public Game() {
    }

    public void play() {
        scanner = new Scanner(System.in);

        int turn = 0;
        clearScreen();

        k = getIntUserInput("Type number k to play Kinarow: ");
        while (k < 4 || k > 10) {
            k = getIntUserInput("Number should be in range(4 - 10). Try Again: ");
        }
        clearScreen();

        board = new Board(k);
        board.printBoard();

        int gameState;
        do {
            if (turn % 2 == 0) {
                gameState = playTurn(board.PLAYER1, 1);
            } else {
                gameState = playTurn(board.PLAYER2, 2);
            }
            turn++;

        } while (gameState == CONTINUE);

        printGameResult(gameState);

        scanner.close();
    }

    private void printGameResult(int gameState) {
        switch (gameState) {
            case DRAW:
                System.out.println(board.CYAN + "Game Ended With A Draw :|" + board.RESET);
                break;

            case PLAYER1_WON:
                System.out.println(board.PURPLE + "Player 1 Won!" + board.RESET);
                break;

            case PLAYER2_WON:
                System.out.println(board.PURPLE + "Player 2 Won!" + board.RESET);
                break;
        }
    }

    private int playTurn(char player, int id) {
        int col = getIntUserInput("Player " + id + " place your piece in column number: ");
        col--;

        while (board.place(col, player) == false) {
            col = getIntUserInput("Invaild placment. Please try Again: ");
            col--;
        }
        clearScreen();

        board.printBoard();

        int row = board.findRowByCol(col) + 1;
        return findGameState(col, row, player);
    }

    private int findGameState(int col, int row, char player) {
        if (kInARow(col, row, player)) {
            return player == board.PLAYER1 ? PLAYER1_WON : PLAYER2_WON;
        }
        if (board.boardFull()) {
            return DRAW;
        }
        return CONTINUE;
    }

    private boolean kInARow(int col, int row, char player) {
        for (dir dir : dir.values()) {
            if (kInARow(col, row, player, dir.x, dir.y)) {
                return true;
            }
        }
        if (kInARow(col, row, player, dir.UP.x, dir.UP.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.DOWN.x, dir.DOWN.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.LEFT.x, dir.LEFT.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.RIGHT.x, dir.RIGHT.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.DIAGONAL_UL.x, dir.DIAGONAL_UL.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.DIAGONAL_DL.x, dir.DIAGONAL_DL.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.DIAGONAL_UR.x, dir.DIAGONAL_UR.y)) {
            return true;
        }
        if (kInARow(col, row, player, dir.DIAGONAL_DR.x, dir.DIAGONAL_DR.y)) {
            return true;
        }
        return false;
    }

    private boolean kInARow(int col, int row, char player, int x, int y) {
        int count = 0;

        while (!board.isOutOfRange(col, row) && board.board[row][col] == player) {
            count++;
            col += x;
            row += y;
        }
        return count == k;
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

enum dir {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), DIAGONAL_UL(-1, -1),
    DIAGONAL_UR(1, -1), DIAGONAL_DL(-1, 1), DIAGONAL_DR(1, 1);

    int x, y;

    private dir(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

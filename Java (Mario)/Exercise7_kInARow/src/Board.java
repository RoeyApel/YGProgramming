public class Board {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";

    public final char PLAYER1 = '#';
    public final char PLAYER2 = '@';
    public final char EMPTY = '0';

    char[][] board;

    public Board(int k) {
        board = new char[k * 2 - 2][k * 2 - 1];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(getColoredString(board[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public String getColoredString(char ch) {
        if (ch == PLAYER1) {
            return RED + ch + RESET;
        }
        if (ch == PLAYER2) {
            return GREEN + ch + RESET;
        }
        if (ch == EMPTY) {
            return ch + "";
        }
        return "COLOR ERROR";
    }
}

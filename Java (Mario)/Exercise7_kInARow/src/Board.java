public class Board {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";
    private final String YELLOW = "\u001B[33m";

    public final char PLAYER1 = '#';
    public final char PLAYER2 = '$';
    public final char EMPTY = '0';

    char[][] board;
    int[] columnsHights;

    public Board(int k) {
        board = new char[k * 2 - 2][k * 2 - 1];
        columnsHights = new int[k * 2 - 1];
        resetBoard();
    }

    public boolean place(int col, char player) {
        boolean outOfRange = col >= getNumOfCols() || col < 0;
        if (outOfRange) {
            return false;
        }

        boolean columnFull = columnsHights[col] == getNumOfRows();
        if (columnFull) {
            return false;
        }

        int row = board.length - 1 - columnsHights[col];
        board[row][col] = player;

        columnsHights[col]++;
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }
        for (int i = 0; i < columnsHights.length; i++) {
            columnsHights[i] = 0;
        }
    }

    public void printBoard() {
        System.out.println(BLUE + "--Game Board" + RESET);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(getColoredString(board[i][j]) + " ");
            }
            System.out.println();
        }
        int f = 1;
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(BLUE);

            if (f % 10 == 0) {
                f = 0;
                System.out.print(YELLOW);
            }

            System.out.print(f + " ");
            f++;
        }
        System.out.print(RESET);

        System.out.print("\n\n");
    }

    private String getColoredString(char ch) {
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

    public int getNumOfRows() {
        return board.length;
    }

    public int getNumOfCols() {
        return board[0].length;
    }
}

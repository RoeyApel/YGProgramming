public class Board {
    public final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";
    private final String YELLOW = "\u001B[33m";
    public final String PURPLE = "\u001B[35m";
    public final String CYAN = "\u001B[36m";

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

        int row = findRowByCol(col);
        board[row][col] = player;

        columnsHights[col]++;
        return true;
    }

    public int findRowByCol(int col) {
        return board.length - 1 - columnsHights[col];
    }

    public boolean boardFull() {
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public boolean isOutOfRange(int col, int row) {
        boolean outOfRangeCol = col >= getNumOfCols() || col < 0;
        boolean outOfRangeRow = row >= getNumOfRows() || row < 0;

        return outOfRangeCol || outOfRangeRow;
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

        System.out.print(RESET + "\n\n");
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

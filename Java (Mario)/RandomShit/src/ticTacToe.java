public class ticTacToe {

    public static void main(String[] args) {
        char[][] board = new char[][] { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        printBoard(board);

        char player1 = 'O', player2 = 'X';
    }

    private static void clearBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("| " + board[i][j] + " |");
            }
            System.out.println("\n---------------");
        }
    }
}

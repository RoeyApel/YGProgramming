public class BingoBoard {
    int[][] board;
    int[] notInBoard;

    public BingoBoard(int[] deck) {
        notInBoard = new int[2];
        notInBoard[0] = deck[deck.length - 2];
        notInBoard[1] = deck[deck.length - 1];

        initBoard();

        setBoard(deck);
    }

    private void initBoard() {
        board = new int[7][];

        for (int i = 0; i < board.length; i++) {
            board[i] = new int[i + 1];
        }
    }

    private void setBoard(int[] deck) {
        int k = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = deck[k];
                k++;
            }
        }
    }

}
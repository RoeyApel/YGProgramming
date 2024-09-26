public class BingoPlayer {
    String playerId;
    BingoBoard bingoBoard;
    boolean loser;

    public BingoPlayer(int[] deck, int index) {
        playerId = Integer.toString(index + 1) + "#";
        bingoBoard = new BingoBoard(deck);
        loser = false;
    }

    public void printBoard() {
        for (int i = 0; i < bingoBoard.board.length; i++) {
            for (int j = 0; j < bingoBoard.board[i].length; j++) {
                System.out.print(bingoBoard.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void checkIfLoser(int num) {
        if (num == bingoBoard.notInBoard[0] || num == bingoBoard.notInBoard[1]) {
            loser = true;
        }
    }

}

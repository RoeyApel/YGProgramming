public class BingoPlayer {
    static int count = 0;
    String playerId;
    BingoBoard bingoBoard;
    boolean loser;

    public BingoPlayer(int[] deck) {
        count++;
        playerId = Integer.toString(count) + "#";
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

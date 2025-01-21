import java.util.ArrayList;

public class GameLogic {
    private Board board;

    public GameLogic(Board board) {
        this.board = board;
    }

    public ArrayList<Move> getLegalMoves(Position position) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        Slot slot = board.get(position);

        if (slot.getLeft() == Slot.EMPTY) {

        }
    }
}

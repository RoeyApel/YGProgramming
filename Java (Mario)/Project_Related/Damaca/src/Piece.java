import java.awt.Image;

public class Piece {
    public static final int CHECKER = 1;
    public static final int KING = 2;
    public static final int PLAYER = 3;
    public static final int OPPONENT = 4;

    public int id;
    public int status;
    public int row, col;
    public Image image;

    public Piece(int row, int col, int id, int status) {
        this.row = row;
        this.col = col;
        this.id = id;
        this.status = status;

        if (id == PLAYER) {
            image = (status == CHECKER) ? Pieces.PLAYER.getImage() : Pieces.PLAYER_KING.getImage();
        } else {
            image = (status == CHECKER) ? Pieces.OPPONENT.getImage() : Pieces.OPPONENT_KING.getImage();
        }
    }

}

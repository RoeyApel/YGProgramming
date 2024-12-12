import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Board {
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int NUM_OF_PIECES = 24;
    private static final int SLOT_SIZE = GameFrame.GAME_WIDTH / ROWS;
    private static final int OFFSET = 20;

    private ArrayList<Piece> pieces;

    public Board() {
        pieces = new ArrayList<>(NUM_OF_PIECES);

        initPieces();
    }

    public void drawPieces(Graphics g) {
        for (Piece piece : pieces) {
            g.drawImage(piece.image, piece.row * SLOT_SIZE + OFFSET, piece.col * SLOT_SIZE + OFFSET, null);
            System.out.println(piece.image);
        }
    }

    public void drawBoard(Graphics g) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                if (isBlackSlot(i, j)) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }

                g.fillRect(j * SLOT_SIZE, i * SLOT_SIZE, SLOT_SIZE, SLOT_SIZE);
            }
        }
    }

    private boolean isBlackSlot(int i, int j) {
        return j % 2 == 0 && i % 2 == 0 || j % 2 == 1 && i % 2 == 1;
    }

    private void initPieces() {
        int i = 0, j = 1;
        int id = Piece.OPPONENT;
        while (i < ROWS) {
            if (i == 3) {
                i += 2;
                id = Piece.PLAYER;
            }
            j = 1 - j;

            while (j < COLS) {
                pieces.add(new Piece(i, j, id, Piece.CHECKER));
                j += 2;
            }
            i++;
        }
    }
}

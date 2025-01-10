import java.awt.Graphics;

public class Board {
    public static final int GAME_ROWS = 9;
    public static final int GAME_COLS = 9;
    public static final int DATA_ROWS = 2 * GAME_ROWS - 1;
    public static final int DATA_COLS = 2 * GAME_COLS - 1;
    public static final int NUM_OF_SLOTS = GAME_COLS * GAME_ROWS;

    private Piece[][] board;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        board = new Piece[DATA_ROWS][DATA_COLS];

        int kind;
        for (int i = 0; i < DATA_ROWS; i++) {
            for (int j = 0; j < DATA_COLS; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    kind = (i == 0) || (i == DATA_ROWS - 1) ? Slot.WINNING_SLOT : Slot.NORMAL_SLOT;
                    board[i][j] = new Slot(j + i * DATA_COLS, kind);
                } else {
                    board[i][j] = new Wall(j + i * DATA_COLS, false);
                }
            }
        }
    }

    public void drawBoard(Graphics g, int panelWidth, int panelHeight) {
        int slotWidth = panelWidth / GAME_COLS;
        int slotHeight = panelHeight / GAME_ROWS;

        for (int i = 0; i < DATA_ROWS; i += 2) {
            for (int j = 0; j < DATA_COLS; j += 2) {
                board[i][j].setBounds((j / 2) * slotWidth, (i / 2) * slotHeight, slotWidth, slotHeight);
                board[i][j].draw(g);
            }
        }
    }
}

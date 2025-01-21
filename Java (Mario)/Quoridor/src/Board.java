import java.awt.Graphics;

public class Board {
    public static final int ROWS = 9;
    public static final int COLS = 9;
    public static final int PLAYER_START_POS = (ROWS - 1) * COLS + COLS / 2;
    public static final int OPPONENT_START_POS = COLS / 2;

    private Slot[][] board;
    private Character player, opponent;

    public Board() {
        initBoard();
        player = new Character(Characters.PLAYER.getImage(), PLAYER_START_POS / ROWS, PLAYER_START_POS % COLS);
        opponent = new Character(Characters.OPPONENT.getImage(), OPPONENT_START_POS / ROWS, OPPONENT_START_POS % COLS);
    }

    private void initBoard() {
        board = new Slot[ROWS][COLS];

        int kind;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                kind = (i == 0) || (i == ROWS - 1) ? Slot.WINNING_SLOT : Slot.NORMAL_SLOT;
                board[i][j] = new Slot(i, j, kind);
            }
        }
    }

    public void drawBoard(Graphics g, int panelWidth, int panelHeight) {
        int slotWidth = panelWidth / COLS;
        int slotHeight = panelHeight / ROWS;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j].setBounds(j * slotWidth, i * slotHeight, slotWidth, slotHeight);
                board[i][j].draw(g);
            }
        }
        player.setBounds(player.getCol() * slotWidth, player.getRow() * slotHeight, slotWidth, slotHeight);
        opponent.setBounds(opponent.getCol() * slotWidth, opponent.getRow() * slotHeight, slotWidth,
                slotHeight);

        player.draw(g);
        opponent.draw(g);
    }
}

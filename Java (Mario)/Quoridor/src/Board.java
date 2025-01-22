import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Board {
    public static final int ROWS = 9;
    public static final int COLS = 9;
    public static final int PLAYER_START_POS = (ROWS - 1) * COLS + COLS / 2;
    public static final int OPPONENT_START_POS = COLS / 2;

    private Slot[][] board;
    private int slotWidth, slotHeight;
    private Character player, opponent;

    public Board() {
        player = new Character(Characters.PLAYER.getImage(), PLAYER_START_POS / ROWS, PLAYER_START_POS % COLS);
        opponent = new Character(Characters.OPPONENT.getImage(), OPPONENT_START_POS / ROWS, OPPONENT_START_POS % COLS);
        initBoard();
    }

    private void initBoard() {
        board = new Slot[ROWS][COLS];

        Slots kind;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                kind = (i == 0) || (i == ROWS - 1) ? Slots.WINNER : Slots.NORMAL;
                board[i][j] = new Slot(kind, i, j);
            }
        }
        this.get(player.getPosition()).setOcuppied(true);
        this.get(opponent.getPosition()).setOcuppied(true);
    }

    public void drawBoard(Graphics g, int panelWidth, int panelHeight) {
        slotWidth = panelWidth / COLS;
        slotHeight = panelHeight / ROWS;

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

    public void markSlots(ArrayList<Move> moves) {
        for (Move move : moves) {
            this.get(move.getTarget()).mark();
        }
    }

    public void unmarkSlots(ArrayList<Move> moves) {
        for (Move move : moves) {
            this.get(move.getTarget()).unmark();
        }
    }

    public ArrayList<Move> getLegalMoves(Position pos) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        addLegalMovesBottom(legalMoves, pos.row, pos.col);
        addLegalMovesRight(legalMoves, pos.row, pos.col);
        addLegalMovesLeft(legalMoves, pos.row, pos.col);
        addLegalMovesTop(legalMoves, pos.row, pos.col);

        return legalMoves;
    }

    public void addLegalMovesRight(ArrayList<Move> legalMoves, int row, int col) {
        if (board[row][col].getRightWall() != Slot.EMPTY)
            return;

        if (board[row][col + 1].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row, col + 1));
            return;
        }

        if (board[row][col + 1].getRightWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row, col + 2));
            return;
        }

        if (row != 0 && board[row - 1][col + 1].getBottomWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }

        if (board[row][col + 1].getBottomWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }
    }

    public void addLegalMovesLeft(ArrayList<Move> legalMoves, int row, int col) {
        if (col == 0 || board[row][col - 1].getRightWall() != Slot.EMPTY)
            return;

        if (board[row][col - 1].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row, col - 1));
            return;
        }

        if (col != 1 && board[row][col - 2].getRightWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row, col - 2));
            return;
        }

        if (row != 0 && board[row - 1][col - 1].getBottomWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col - 1));
        }

        if (board[row][col - 1].getBottomWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row + 1, col - 1));
        }
    }

    public void addLegalMovesBottom(ArrayList<Move> legalMoves, int row, int col) {
        if (board[row][col].getBottomWall() != Slot.EMPTY)
            return;

        if (board[row + 1][col].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row + 1, col));
            return;
        }

        if (board[row + 1][col].getBottomWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row + 2, col));
            return;
        }

        if (col != 0 && board[row + 1][col - 1].getRightWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row + 1, col - 1));
        }

        if (board[row + 1][col].getRightWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row + 1, col + 1));
        }
    }

    public void addLegalMovesTop(ArrayList<Move> legalMoves, int row, int col) {
        if (row == 0 || board[row - 1][col].getBottomWall() != Slot.EMPTY)
            return;

        if (board[row - 1][col].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row - 1, col));
            return;
        }

        if (row != 1 && board[row - 2][col].getBottomWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row - 2, col));
            return;
        }

        if (col != 0 && board[row - 1][col - 1].getRightWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col - 1));
        }

        if (board[row - 1][col].getRightWall() == Slot.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }
    }

    public Slot get(int row, int col) {
        return board[row][col];
    }

    public Slot get(Position position) {
        return board[position.row][position.col];
    }

    public int getSlotWidth() {
        return slotWidth;
    }

    public void setSlotWidth(int slotWidth) {
        this.slotWidth = slotWidth;
    }

    public int getSlotHeight() {
        return slotHeight;
    }

    public void setSlotHeight(int slotHeight) {
        this.slotHeight = slotHeight;
    }

    public Position getPlayerPos() {
        return player.getPosition();
    }

    public void setPlayerPos(int row, int col) {
        player.setPosition(row, col);
    }

    public void setPlayerMoves(ArrayList<Move> moves) {
        player.setMoves(moves);
    }

    public ArrayList<Move> getPlayerMoves() {
        return player.getMoves();
    }
}

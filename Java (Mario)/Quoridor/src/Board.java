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
                if (true && j % 2 == 1) {
                    board[i][j].setBottomWall(Walls.SELECTED_WALL);
                    board[i][j].setRightWall(Walls.SELECTED_WALL);
                }
                board[i][j].draw(g);
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j].drawWalls(g);
            }
        }

        player.setBounds(player.getCol() * slotWidth, player.getRow() * slotHeight, slotWidth, slotHeight);
        opponent.setBounds(opponent.getCol() * slotWidth, opponent.getRow() * slotHeight, slotWidth,
                slotHeight);

        player.draw(g);
        opponent.draw(g);
    }

    public void placeWall(Wall wall) {
        int row = wall.position.row;
        int col = wall.position.col;
        Walls type = wall.type;

        if (wall.direction == Directions.LEFT) {
            board[row][col].setBottomWall(type);
            board[row][col - 1].setBottomWall(type);
        } else if (wall.direction == Directions.RIGHT) {
            board[row - 1][col].setBottomWall(type);
            board[row - 1][col + 1].setBottomWall(type);
        } else if (wall.direction == Directions.UP) {
            board[row][col].setRightWall(type);
            board[row - 1][col].setRightWall(type);
        } else if (wall.direction == Directions.DOWN) {
            board[row][col].setRightWall(type);
            board[row + 1][col].setRightWall(type);
        }
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
        if (board[row][col].getRightWall() != Walls.EMPTY)
            return;

        if (board[row][col + 1].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row, col + 1));
            return;
        }

        if (board[row][col + 1].getRightWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row, col + 2));
            return;
        }

        if (row != 0 && board[row - 1][col + 1].getBottomWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }

        if (board[row][col + 1].getBottomWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }
    }

    public void addLegalMovesLeft(ArrayList<Move> legalMoves, int row, int col) {
        if (col == 0 || board[row][col - 1].getRightWall() != Walls.EMPTY)
            return;

        if (board[row][col - 1].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row, col - 1));
            return;
        }

        if (col != 1 && board[row][col - 2].getRightWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row, col - 2));
            return;
        }

        if (row != 0 && board[row - 1][col - 1].getBottomWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col - 1));
        }

        if (board[row][col - 1].getBottomWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row + 1, col - 1));
        }
    }

    public void addLegalMovesBottom(ArrayList<Move> legalMoves, int row, int col) {
        if (board[row][col].getBottomWall() != Walls.EMPTY)
            return;

        if (board[row + 1][col].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row + 1, col));
            return;
        }

        if (board[row + 1][col].getBottomWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row + 2, col));
            return;
        }

        if (col != 0 && board[row + 1][col - 1].getRightWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row + 1, col - 1));
        }

        if (board[row + 1][col].getRightWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row + 1, col + 1));
        }
    }

    public void addLegalMovesTop(ArrayList<Move> legalMoves, int row, int col) {
        if (row == 0 || board[row - 1][col].getBottomWall() != Walls.EMPTY)
            return;

        if (board[row - 1][col].isOcuppied() == false) {
            legalMoves.add(new Move(row, col, row - 1, col));
            return;
        }

        if (row != 1 && board[row - 2][col].getBottomWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row - 2, col));
            return;
        }

        if (col != 0 && board[row - 1][col - 1].getRightWall() == Walls.EMPTY) {
            legalMoves.add(new Move(row, col, row - 1, col - 1));
        }

        if (board[row - 1][col].getRightWall() == Walls.EMPTY) {
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

    public Character getPlayer() {
        return player;
    }

    public Character getOpponent() {
        return opponent;
    }
}

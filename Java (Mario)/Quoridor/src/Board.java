import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Board {
    public static final int ROWS = 9;
    public static final int COLS = 9;
    public static final int PLAYER_START_POS = (ROWS - 1) * COLS + COLS / 2;
    public static final int OPPONENT_START_POS = COLS / 2;

    private Slot[][] board;
    private int slotWidth, slotHeight;
    private Character player, opponent;

    public Board() {
        player = new Character(Characters.PLAYER.getImage(), PLAYER_START_POS / ROWS, PLAYER_START_POS % COLS, 0);
        opponent = new Character(Characters.OPPONENT.getImage(), OPPONENT_START_POS / ROWS, OPPONENT_START_POS % COLS, ROWS - 1);
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

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j].drawWalls(g);
            }
        }

        player.setBounds(player.getCol() * slotWidth, player.getRow() * slotHeight, slotWidth, slotHeight);
        opponent.setBounds(opponent.getCol() * slotWidth, opponent.getRow() * slotHeight, slotWidth, slotHeight);

        player.draw(g);
        opponent.draw(g);
    }

    public void placeWall(Wall wall) {
        int row = wall.position.row;
        int col = wall.position.col;
        Walls type = wall.type;

        if (wall.direction == Directions.LEFT) {
            board[row - 1][col].setBottomWall(type);
            board[row - 1][col - 1].setBottomWall(type);
        }
        else if (wall.direction == Directions.RIGHT) {
            board[row][col].setBottomWall(type);
            board[row][col + 1].setBottomWall(type);
        }
        else if (wall.direction == Directions.UP) {
            board[row][col].setRightWall(type);
            board[row - 1][col].setRightWall(type);
        }
        else if (wall.direction == Directions.DOWN) {
            board[row][col - 1].setRightWall(type);
            board[row + 1][col - 1].setRightWall(type);
        }
    }

    public void removeWall(Wall wall) {
        int row = wall.position.row;
        int col = wall.position.col;
        Walls type = Walls.EMPTY;

        if (wall.direction == Directions.LEFT) {
            board[row - 1][col].setBottomWall(type);
            board[row - 1][col - 1].setBottomWall(type);
        }
        else if (wall.direction == Directions.RIGHT) {
            board[row][col].setBottomWall(type);
            board[row][col + 1].setBottomWall(type);
        }
        else if (wall.direction == Directions.UP) {
            board[row][col].setRightWall(type);
            board[row - 1][col].setRightWall(type);
        }
        else if (wall.direction == Directions.DOWN) {
            board[row][col - 1].setRightWall(type);
            board[row + 1][col - 1].setRightWall(type);
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

    public void markPath(ArrayList<Position> path) {
        for (Position position : path) {
            this.get(position).mark();
        }
    }

    public void unmarkPath(ArrayList<Position> path) {
        for (Position position : path) {
            this.get(position).unmark();
        }
    }

    public Queue<Wall> getPlacableWalls(int row, int col) {
        Queue<Wall> placableWalls = new LinkedList<>();

        if (isLegalPlacementUP(row, col)) {
            placableWalls.add(new Wall(Walls.SELECTED_WALL, row, col, Directions.UP));
        }
        if (isLegalPlacementLeft(row, col)) {
            placableWalls.add(new Wall(Walls.SELECTED_WALL, row, col, Directions.LEFT));
        }
        if (isLegalPlacementDown(row, col)) {
            placableWalls.add(new Wall(Walls.SELECTED_WALL, row, col, Directions.DOWN));
        }
        if (isLegalPlacementRight(row, col)) {
            placableWalls.add(new Wall(Walls.SELECTED_WALL, row, col, Directions.RIGHT));
        }
        return placableWalls;
    }

    private boolean isLegalPlacementRight(int row, int col) {
        return row < ROWS - 1 && col < COLS - 1 && !board[row][col].hasBottomWall() && !board[row][col + 1].hasBottomWall() && !board[row][col].hasRightWall();
    }

    private boolean isLegalPlacementDown(int row, int col) {
        return row < ROWS - 1 && col > 0 && !board[row][col - 1].hasRightWall() && !board[row + 1][col - 1].hasRightWall() && !board[row][col].hasBottomWall();
    }

    private boolean isLegalPlacementLeft(int row, int col) {
        return col > 0 && row > 0 && !board[row - 1][col].hasBottomWall() && !board[row - 1][col - 1].hasBottomWall() && !board[row][col - 1].hasRightWall();
    }

    private boolean isLegalPlacementUP(int row, int col) {
        return col < COLS - 1 && row > 0 && !board[row][col].hasRightWall() && !board[row - 1][col].hasRightWall() && !board[row - 1][col].hasBottomWall();
    }

    public ArrayList<Move> getLegalMoves(int row, int col) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        addLegalMovesBottom(legalMoves, row, col);
        addLegalMovesRight(legalMoves, row, col);
        addLegalMovesLeft(legalMoves, row, col);
        addLegalMovesTop(legalMoves, row, col);

        return legalMoves;
    }

    public ArrayList<Move> getLegalMoves(Position position) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        addLegalMovesBottom(legalMoves, position.row, position.col);
        addLegalMovesRight(legalMoves, position.row, position.col);
        addLegalMovesLeft(legalMoves, position.row, position.col);
        addLegalMovesTop(legalMoves, position.row, position.col);

        return legalMoves;
    }

    public void addLegalMovesRight(ArrayList<Move> legalMoves, int row, int col) {
        if (col == COLS - 1 || board[row][col].hasRightWall())
            return;

        if (!board[row][col + 1].isOcuppied()) {
            legalMoves.add(new Move(row, col, row, col + 1));
            return;
        }

        if (col + 2 < COLS && !board[row][col + 1].hasRightWall()) {
            legalMoves.add(new Move(row, col, row, col + 2));
            return;
        }

        if (row != 0 && !board[row - 1][col + 1].hasBottomWall()) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }

        if (!board[row][col + 1].hasBottomWall()) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }
    }

    public void addLegalMovesLeft(ArrayList<Move> legalMoves, int row, int col) {
        if (col == 0 || board[row][col - 1].hasRightWall())
            return;

        if (!board[row][col - 1].isOcuppied()) {
            legalMoves.add(new Move(row, col, row, col - 1));
            return;
        }

        if (col != 1 && !board[row][col - 2].hasRightWall()) {
            legalMoves.add(new Move(row, col, row, col - 2));
            return;
        }

        if (row != 0 && !board[row - 1][col - 1].hasBottomWall()) {
            legalMoves.add(new Move(row, col, row - 1, col - 1));
        }

        if (!board[row][col - 1].hasBottomWall()) {
            legalMoves.add(new Move(row, col, row + 1, col - 1));
        }
    }

    public void addLegalMovesBottom(ArrayList<Move> legalMoves, int row, int col) {
        if (row == ROWS - 1 || board[row][col].hasBottomWall())
            return;

        if (!board[row + 1][col].isOcuppied()) {
            legalMoves.add(new Move(row, col, row + 1, col));
            return;
        }

        if (row + 2 < ROWS && !board[row + 1][col].hasBottomWall()) {
            legalMoves.add(new Move(row, col, row + 2, col));
            return;
        }

        if (col > 0 && !board[row + 1][col - 1].hasRightWall()) {
            legalMoves.add(new Move(row, col, row + 1, col - 1));
        }

        if (!board[row + 1][col].hasRightWall()) {
            legalMoves.add(new Move(row, col, row + 1, col + 1));
        }
    }

    public void addLegalMovesTop(ArrayList<Move> legalMoves, int row, int col) {
        if (row == 0 || board[row - 1][col].hasBottomWall())
            return;

        if (!board[row - 1][col].isOcuppied()) {
            legalMoves.add(new Move(row, col, row - 1, col));
            return;
        }

        if (row != 1 && !board[row - 2][col].hasBottomWall()) {
            legalMoves.add(new Move(row, col, row - 2, col));
            return;
        }

        if (col != 0 && !board[row - 1][col - 1].hasRightWall()) {
            legalMoves.add(new Move(row, col, row - 1, col - 1));
        }

        if (!board[row - 1][col].hasRightWall()) {
            legalMoves.add(new Move(row, col, row - 1, col + 1));
        }
    }

    public boolean isMarked(int row, int col) {
        return board[row][col].isMarked();
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

    private boolean outOfBounds(int row, int col) {
        return row >= ROWS || col >= COLS || row < 0 || col < 0;
    }
}

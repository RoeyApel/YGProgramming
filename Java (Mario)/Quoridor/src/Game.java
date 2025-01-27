import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.SwingUtilities;

public class Game implements MouseListener, KeyListener {
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Board board;
    private Character currentPlayer;
    private Queue<Wall> wallsOptions = new LinkedList<>();
    private boolean wallSelectionActive, moveSelectionActive;
    private Position lastSlotClicked = new Position(-1, -1);
    private boolean gameOver;

    public Game() {
        board = new Board();
        currentPlayer = board.getPlayer();
        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        gamePanel = new GamePanel(this);

        gamePanel.addKeyListener(this);
        gamePanel.addMouseListener(this);

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gameFrame.add(gamePanel);

        gameFrame.setVisible(true);
    }

    public void render(Graphics g, int panelWidth, int panelHeight) {
        board.drawBoard(g, panelWidth, panelHeight);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameOver)
            return;

        int row = e.getY() / board.getSlotHeight();
        int col = e.getX() / board.getSlotWidth();

        if (currentPlayer.isAt(row, col)) {
            onClickCurrentPlayer(row, col);
        }
        else if (board.isMarked(row, col)) {
            onClickMarkedSlot(row, col);
        }
        else if (SwingUtilities.isLeftMouseButton(e)) {
            onLeftClickSlot(row, col);
        }
        else if (SwingUtilities.isRightMouseButton(e) && wallSelectionActive) {
            onRightClickSlot(row, col);
        }

        gamePanel.repaint();
    }

    private void endTurn() {
        if (currentPlayer.hasWon()) {
            gameOver = true;
            System.out.println("player:" + currentPlayer + " has won");
        }

        reset();

        currentPlayer = board.getOpponent().isAt(currentPlayer.getPosition()) ? board.getPlayer() : board.getOpponent();
    }

    private void onRightClickSlot(int row, int col) {
        Wall wall = wallsOptions.poll();
        wall.type = Walls.WALL;

        board.placeWall(wall);

        currentPlayer.decWallsCount();

        endTurn();

        // **more checks before placing wall.
    }

    private void onLeftClickSlot(int row, int col) {
        if (moveSelectionActive) {
            deactivateMoveSelection(row, col);
        }

        if (lastSlotClicked.equals(row, col)) {
            displayNextWallOption(row, col);
        }
        else {
            if (wallSelectionActive) {
                deactivateWallSelection(row, col);
            }

            activateWallSelection(row, col);
        }
        lastSlotClicked.setPosition(row, col);
    }

    private boolean activateWallSelection(int row, int col) {
        wallsOptions = board.getPlacableWalls(row, col);

        if (wallsOptions.isEmpty()) {
            return false;
        }

        board.placeWall(wallsOptions.peek());
        wallSelectionActive = true;
        return true;
    }

    private boolean displayNextWallOption(int row, int col) {
        if (wallsOptions.isEmpty())
            return false;

        board.removeWall(wallsOptions.peek());

        wallsOptions.add(wallsOptions.poll());

        board.placeWall(wallsOptions.peek());
        return true;
    }

    private void onClickMarkedSlot(int row, int col) {
        deactivateMoveSelection(row, col);

        moveCurrentPlayer(row, col);

        endTurn();
    }

    private void reset() {
        wallSelectionActive = false;
        moveSelectionActive = false;
        currentPlayer.setMoves(null);
        wallsOptions = null;
        lastSlotClicked.col = -1;
        lastSlotClicked.row = -1;
    }

    private void moveCurrentPlayer(int row, int col) {
        Position currentPosition = currentPlayer.getPosition();

        board.get(currentPosition.row, currentPosition.col).setOcuppied(false);
        board.get(row, col).setOcuppied(true);

        currentPlayer.setPosition(row, col);
    }

    private void onClickCurrentPlayer(int row, int col) {
        if (wallSelectionActive) {
            deactivateWallSelection(row, col);
        }

        if (moveSelectionActive) {
            deactivateMoveSelection(row, col);
        }
        else {
            activateMoveSelection(row, col);
        }
        lastSlotClicked.setPosition(row, col);
    }

    private void activateMoveSelection(int row, int col) {
        ArrayList<Move> moves = board.getLegalMoves(row, col);
        currentPlayer.setMoves(moves);
        board.markSlots(moves);
        moveSelectionActive = true;
    }

    private void deactivateMoveSelection(int row, int col) {
        ArrayList<Move> moves = currentPlayer.getMoves();
        board.unmarkSlots(moves);
        currentPlayer.setMoves(null);
        moveSelectionActive = false;
    }

    private void deactivateWallSelection(int row, int col) {
        Wall wall = wallsOptions.peek();
        board.removeWall(wall);
        wallsOptions = null;
        wallSelectionActive = false;
    }

    private void printDebug(MouseEvent e, int row, int col) {
        System.out.println("======================================================");
        System.out.println("wsa: " + wallSelectionActive);
        System.out.println("msa: " + moveSelectionActive);
        System.out.println("irc: " + SwingUtilities.isRightMouseButton(e));
        System.out.println("ilc: " + SwingUtilities.isLeftMouseButton(e));
        System.out.println("ismarked: " + board.isMarked(row, col));
        System.out.println("lastSlot: " + lastSlotClicked.row + "|" + lastSlotClicked.col);
        System.out.println("======================================================");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

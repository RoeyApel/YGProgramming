
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game implements MouseListener, KeyListener {

    private static final int NONE = -1;
    private static final int CURRENT_PLAYER = 0;
    private static final int SLOT = 1;
    private static final int MARKED_SLOT = 2;

    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Board board;
    private Character currentPlayer;
    private Queue<Wall> wallSelection = new LinkedList<>();
    private int lastClick = NONE;
    private Position lastSlotClicked = new Position(-1, -1);

    public Game() {
        board = new Board();
        currentPlayer = board.getPlayer();
        initFrame();
    }

    // Todo: kill myself. fuck. right better code!! sigh.

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
        int row = e.getY() / board.getSlotHeight();
        int col = e.getX() / board.getSlotWidth();

        if (currentPlayer.isAt(row, col)) {
            onClickCurrentPlayer(row, col);
            lastClick = CURRENT_PLAYER;
        }
        else if (board.get(row, col).isMarked()) {
            onClickMarkedSlot(row, col);
            lastClick = MARKED_SLOT;
        }
        else {
            onClickSlot(row, col);
            lastClick = SLOT;
        }

        gamePanel.repaint();
        lastSlotClicked.setPosition(row, col);
    }

    private void onClickMarkedSlot(int row, int col) {
        board.get(currentPlayer.getPosition()).setOcuppied(false);

        currentPlayer.setPosition(row, col);

        board.get(currentPlayer.getPosition()).setOcuppied(true);

        board.unmarkSlots(currentPlayer.getMoves());
    }

    private void onClickSlot(int row, int col) {
        if (lastClick == CURRENT_PLAYER) {
            board.unmarkSlots(currentPlayer.getMoves());
        }
        if (lastSlotClicked.equals(row, col)) {
            board.removeWall(wallSelection.peek());

            wallSelection.add(wallSelection.poll());

            board.placeWall(wallSelection.peek());

            wallSelection.add(wallSelection.poll());
        }
        else if (!lastSlotClicked.equals(row, col)) {
            if (!wallSelection.isEmpty()) {
                board.removeWall(wallSelection.peek());
            }

            wallSelection = board.getPlacableWalls(row, col);

            board.placeWall(wallSelection.peek());
        }

    }

    private void onClickCurrentPlayer(int row, int col) {
        if (lastClick != CURRENT_PLAYER) {
            ArrayList<Move> legalMoves = board.getLegalMoves(currentPlayer.getPosition());
            currentPlayer.setMoves(legalMoves);

            board.markSlots(currentPlayer.getMoves());

        }
        else {
            board.unmarkSlots(currentPlayer.getMoves());
        }
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
        if (e.getKeyCode() == KeyEvent.VK_E) {
            currentPlayer = board.getOpponent().isAt(currentPlayer.getPosition()) ? board.getPlayer() : board.getOpponent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

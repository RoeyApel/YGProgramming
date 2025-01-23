import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Game implements MouseListener, KeyListener {
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Board board;
    private Character currentPlayer;
    private boolean playerSelected;

    public Game() {
        board = new Board();
        currentPlayer = board.getPlayer();
        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        gamePanel = new GamePanel(this);

        gameFrame.addKeyListener(this);
        gamePanel.addMouseListener(this);

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

        if (!playerSelected) {
            if (currentPlayer.isAt(row, col)) {
                playerSelected = true;

                ArrayList<Move> legalMoves = board.getLegalMoves(currentPlayer.getPosition());
                currentPlayer.setMoves(legalMoves);

                board.markSlots(currentPlayer.getMoves());

                gamePanel.repaint();
            }
        } else {
            if (board.get(row, col).isMarked()) {
                board.get(currentPlayer.getPosition()).setOcuppied(false);

                currentPlayer.setPosition(row, col);

                board.get(currentPlayer.getPosition()).setOcuppied(true);

            }

            playerSelected = false;
            board.unmarkSlots(currentPlayer.getMoves());

            gamePanel.repaint();
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
            currentPlayer = board.getOpponent().isAt(currentPlayer.getPosition()) ? board.getPlayer()
                    : board.getOpponent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

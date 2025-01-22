import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game implements MouseListener {
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Board board;
    private boolean playerSelected;

    public Game() {
        board = new Board();

        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        gamePanel = new GamePanel(this);

        gamePanel.addMouseListener(this);

        gameFrame.add(gamePanel);

        gameFrame.setVisible(true);
    }

    public void render(Graphics g, int panelWidth, int panelHeight) {
        board.drawBoard(g, panelWidth, panelHeight);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = e.getY() / board.getSlotHeight();
        int col = e.getX() / board.getSlotWidth();

        System.out
                .println("pressed " + row + " " + col + " || " + board.getPlayerPos().row + " "
                        + board.getPlayerPos().col);

        if (!playerSelected) {
            if (board.getPlayerPos().equals(row, col)) {
                playerSelected = true;

                board.setPlayerMoves(board.getLegalMoves(board.getPlayerPos()));
                board.markSlots(board.getPlayerMoves());

                gamePanel.repaint();
            }
        } else {
            if (board.get(row, col).isMarked()) {
                board.get(board.getPlayerPos()).setOcuppied(false);
                board.setPlayerPos(row, col);
                board.get(board.getPlayerPos()).setOcuppied(true);
            }

            playerSelected = false;
            board.unmarkSlots(board.getPlayerMoves());

            gamePanel.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

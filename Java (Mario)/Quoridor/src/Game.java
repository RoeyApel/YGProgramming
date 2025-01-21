import java.awt.Graphics;

public class Game {
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Board board;

    public Game() {
        board = new Board();

        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        gamePanel = new GamePanel(this);
        gameFrame.add(gamePanel);

        gameFrame.setVisible(true);
    }

    public void render(Graphics g, int panelWidth, int panelHeight) {
        board.drawBoard(g, panelWidth, panelHeight);
    }
}

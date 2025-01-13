import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.HashSet;

public class Game implements KeyListener {
    public static final int SIZE = 30;

    private int dx, dy;
    private Snake snake;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private boolean gameOver;

    public Game() {
        gameOver = false;
        snake = new Snake();

        gameFrame = new GameFrame();
        gameFrame.addKeyListener(this);

        gamePanel = new GamePanel(this);
        gameFrame.add(gamePanel);

        gameFrame.requestFocus();
        gameFrame.setVisible(true);

        gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    public void render(Graphics g) {
        drawBackground(g);

        if (gameOver) {
            displayGameOver(g);
            return;
        }

        snake.drawSnake(g, dx, dy);
    }

    public void update() {
        dx = gamePanel.getWidth() / SIZE;
        dy = gamePanel.getHeight() / SIZE;

        snake.update();
        if (snake.collidsWithItself() || snake.outOfBounds()) {
            gameOver = true;
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(164, 230, 179));
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                snake.changeDirection(Directions.LEFT);
                break;
            case 'd':
                snake.changeDirection(Directions.RIGHT);
                break;
            case 'w':
                snake.changeDirection(Directions.UP);
                break;
            case 's':
                snake.changeDirection(Directions.DOWN);
                break;
            case 'e':
                snake.addSnakePart();
                break;
        }
    }

    private void displayGameOver(Graphics g) {
        String txtGameOver = "Game Over";
        g.setColor(new Color(200, 50, 50));

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int fontWidth = g.getFontMetrics().stringWidth(txtGameOver);
        int fontHeight = g.getFontMetrics().getHeight();
        g.drawString(txtGameOver, (SIZE / 2) * dx - fontWidth / 2, (SIZE / 2) * dy - fontHeight / 6);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

}

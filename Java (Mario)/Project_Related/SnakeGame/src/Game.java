import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    public static final int SIZE = 30;

    private int dx, dy;
    private Snake snake;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private GameLoop gameLoop;

    public Game() {
        snake = new Snake();

        gameFrame = new GameFrame();
        gameFrame.addKeyListener(this);

        gamePanel = new GamePanel(this);
        gameFrame.add(gamePanel);

        gameFrame.setVisible(true);

        gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    public void render(Graphics g) {
        drawBackground(g);

        snake.drawSnake(g, dx, dy);
    }

    public void update() {
        dx = gamePanel.getWidth() / SIZE;
        dy = gamePanel.getHeight() / SIZE;

        snake.update();
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(164, 230, 179));
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                snake.changeDirection(Snake.LEFT);
                break;
            case 'd':
                snake.changeDirection(Snake.RIGHT);
                break;
            case 'w':
                snake.changeDirection(Snake.UP);
                break;
            case 's':
                snake.changeDirection(Snake.DOWN);
                break;
            case 'e':
                snake.addSnakePart();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

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
    private AppleFactory appleFactory;
    private boolean gameOver;
    private int spawnTick;
    private boolean showGrid;

    public Game() {
        showGrid = false;
        gameOver = false;
        snake = new Snake();
        appleFactory = new AppleFactory(snake);
        spawnTick = AppleFactory.SPAWN_INTERVAL;

        gameFrame = new GameFrame();
        gameFrame.addKeyListener(this);

        gamePanel = new GamePanel(this);
        gameFrame.add(gamePanel);

        gameFrame.pack();
        gameFrame.requestFocus();
        gameFrame.setVisible(true);

        gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    public void render(Graphics g) {
        drawBackground(g);

        if (gameOver) {
            displayGameOver(g);
        } else {
            snake.drawSnake(g, dx, dy);
            appleFactory.drawApples(g, dx, dy);
        }

        if (showGrid)
            drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(i * dx, j * dy, dx, dy);
            }
        }
    }

    public void update() {
        if (gameOver) {
            return;
        }

        dx = gamePanel.getWidth() / SIZE;
        dy = gamePanel.getHeight() / SIZE;

        snake.update();
        appleFactory.update();

        if (snake.collidsWithItself() || snake.outOfBounds()) {
            gameOver = true;
        }

        if (spawnTick >= AppleFactory.SPAWN_INTERVAL) {
            appleFactory.spawnApple();
            spawnTick = 0;
        } else {
            spawnTick++;
        }
    }

    private void resetGame() {
        gameOver = false;
        snake = new Snake();
        appleFactory = new AppleFactory(snake);
        spawnTick = AppleFactory.SPAWN_INTERVAL;
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.decode("#54dc97"));
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
    }

    private void displayGameOver(Graphics g) {
        String txtGameOver = "Game Over";
        g.setColor(new Color(200, 70, 50));

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 2 * (dy + dx)));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int fontWidth = g.getFontMetrics().stringWidth(txtGameOver);
        int fontHeight = g.getFontMetrics().getHeight();
        g.drawString(txtGameOver, (SIZE / 2) * dx - fontWidth / 2, (SIZE / 2) * dy - fontHeight / 8);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            resetGame();

        } else if (key == KeyEvent.VK_P) {
            showGrid = showGrid ? false : true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            snake.changeDirection(Directions.LEFT);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            snake.changeDirection(Directions.RIGHT);
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            snake.changeDirection(Directions.UP);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            snake.changeDirection(Directions.DOWN);
        }
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

    public GameFrame getGameFrame() {
        return gameFrame;
    }

}

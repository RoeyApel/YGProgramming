import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.HashSet;

public class Game implements KeyListener {
    public static final int SIZE = 30;
    public static final int POINTS_PER_APPLE = 1;

    private int dx, dy;
    private Snake snake;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private AppleFactory appleFactory;
    private boolean gameOver;
    private int spawnTick;
    private boolean showGrid;
    private int score;

    public Game() {
        showGrid = false;
        gameOver = false;
        score = 0;
        snake = new Snake();
        appleFactory = new AppleFactory(this);
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
            drawScore(g, Directions.DOWN);
        } else {
            snake.drawSnake(g, dx, dy);
            appleFactory.drawApples(g, dx, dy);
            drawScore(g, Directions.UP);
        }

        if (showGrid)
            drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));

        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
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

    public void increaseScore(int points) {
        score += points;
    }

    private void drawScore(Graphics g, Directions place) {
        String txtScore = score + "";
        if (place == Directions.DOWN) {
            txtScore = "Your Score: " + score;
        }

        setGameFont(g, 3 * (dy / 3 + dx / 3), Font.PLAIN);

        int fontWidth = g.getFontMetrics().stringWidth(txtScore);
        int fontHeight = g.getFontMetrics().getHeight();

        int x, y;
        x = (SIZE / 2) * dx - fontWidth / 2;

        if (place == Directions.UP) {
            g.setColor(new Color(0, 0, 0, 220));
            y = (SIZE / 8) * dy - dy / 4;
        } else {
            g.setColor(new Color(0, 0, 0, 240));
            y = (SIZE / 2 + 3) * dy;
        }

        g.drawString(txtScore, x, y);
    }

    private void setGameFont(Graphics g, int size, int style) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src\\fonts\\Poppins-Regular.ttf"))
                    .deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            font = new Font("Arial", Font.PLAIN, 24);
        }

        g.setFont(font);

        // make no ugly font
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void resetGame() {
        score = 0;
        gameOver = false;
        snake = new Snake();
        appleFactory = new AppleFactory(this);
        spawnTick = AppleFactory.SPAWN_INTERVAL;
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.decode("#54dc97"));
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
    }

    private void displayGameOver(Graphics g) {
        String txtGameOver = "Game Over";
        g.setColor(Color.black);

        setGameFont(g, 2 * (dx + dy), Font.BOLD);

        int fontWidth = g.getFontMetrics().stringWidth(txtGameOver);
        int fontHeight = g.getFontMetrics().getHeight();
        g.drawString(txtGameOver, (SIZE / 2) * dx - fontWidth / 2, (SIZE / 2) * dy);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            if (gameOver) {
                resetGame();
            }

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

    public Snake getSnake() {
        return snake;
    }
}

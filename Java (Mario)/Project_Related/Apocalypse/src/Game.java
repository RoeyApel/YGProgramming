import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.RenderingHints;

public class Game {
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private Mouse mouse;
    private GameStates gameState;
    private Playing playing;
    private Menu menu;
    private int score;

    public Game() {
        gameLoop = new GameLoop(this);
        mouse = new Mouse();
        gameState = GameStates.PLAYING;
        playing = new Playing(this);
        menu = new Menu(this);
        score = 0;

        initFrame();

        gameLoop.start();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        gamePanel = new GamePanel(this);
        gamePanel.addMouseListener(mouse);
        gamePanel.addMouseMotionListener(mouse);

        gameFrame.add(gamePanel);

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    public void draw(Graphics g) {
        drawBackground(g);

        switch (gameState) {
            case PLAYING:
                playing.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
        }
    }

    private void drawBackground(Graphics g) {
        g.drawImage(Images.SPACE.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, gameFrame);
    }

    public void drawScore(Graphics g, String pos) {
        String txtScore = score + "";
        g.setColor(new Color(200, 200, 200, 220));

        setGameFont(g, 32 * pos.length(), Font.PLAIN);

        int fontWidth = g.getFontMetrics().stringWidth(txtScore);
        int fontHeight = g.getFontMetrics().getHeight();

        int x = GamePanel.WIDTH / 2 - fontWidth / 2;

        int y = 0;
        if (pos.equals("top")) {
            y = GamePanel.HEIGHT / 8;
        } else if (pos.equals("center")) {
            y = GamePanel.HEIGHT / 2;
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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void update() {
        switch (gameState) {
            case PLAYING:
                playing.update();
                break;
            case MENU:
                menu.update();
                break;
        }
    }

    public enum GameStates {
        PLAYING, MENU;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    public void increaseScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }
}

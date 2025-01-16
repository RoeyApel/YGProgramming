import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private GameStates gameState;
    private GameLoop gameLoop;
    private Playing playing;
    private Menu menu;

    public Game() {
        playing = new Playing();
        menu = new Menu();

        gameState = GameStates.PLAYING;

        gameFrame = new GameFrame();
        gameFrame.addKeyListener(this);
        gamePanel = new GamePanel(this);
        gameFrame.add(gamePanel);

        gameFrame.pack();
        gameFrame.setVisible(true);

        gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    public void render(Graphics g) {
        g.drawImage(Images.BACKGROUND.getImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), null);

        switch (gameState) {
            case PLAYING:
                playing.render(g);
                break;
            case MENU:
                menu.render(g);
                break;
        }
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

    @Override
    public void keyPressed(KeyEvent e) {
        switch (gameState) {
            case PLAYING:
                playing.onKeyPressed(e);

                break;
            case MENU:
                menu.onKeyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (gameState) {
            case PLAYING:
                playing.onKeyReleased(e);
                break;
            case MENU:
                menu.onKeyReleased(e);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    enum GameStates {
        PLAYING, MENU;
    }

    public GamePanel getPanel() {
        return gamePanel;
    }
}

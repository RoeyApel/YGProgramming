import java.awt.Graphics;

public class Game {
    GameFrame gameFrame;
    GamePanel gamePanel;

    public Game() {
        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        gamePanel = new GamePanel(this);
        gameFrame.add(gamePanel);

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    public void render(Graphics g) {

    }

    public void update(double deltaTime) {

    }
}

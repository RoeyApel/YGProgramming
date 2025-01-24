import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 850;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.draw(g);
    }
}

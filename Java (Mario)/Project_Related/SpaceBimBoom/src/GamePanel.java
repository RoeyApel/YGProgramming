import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public static final int WIDTH = 950;
    public static final int HEIGHT = 800;
    private Game game;

    public GamePanel(Game game) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }
}

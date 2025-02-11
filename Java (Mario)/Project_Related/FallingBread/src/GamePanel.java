import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 850;
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

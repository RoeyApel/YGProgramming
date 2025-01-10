import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g, getWidth(), getHeight());
    }
}

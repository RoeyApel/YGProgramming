import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        this.setPreferredSize(game.getGameFrame().getSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

}

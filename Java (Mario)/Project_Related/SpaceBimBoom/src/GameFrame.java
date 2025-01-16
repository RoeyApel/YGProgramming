import java.awt.Image;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(Images.GOOD_SHIP.getImage());
    }
}

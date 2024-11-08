import java.awt.Image;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public static final int GAME_WIDTH = 360;
    public static final int GAME_HEIGHT = 640;
    private static final String TITLE = "Flappy Bird";

    public GameFrame() {
        super(TITLE);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(Images.BIRD.getImage());
    }
}

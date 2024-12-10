import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private static final String TITLE = "Damaca";
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 800;

    public GameFrame() {
        this.setTitle(TITLE);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

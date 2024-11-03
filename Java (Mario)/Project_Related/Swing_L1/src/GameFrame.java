import javax.swing.*;

public class GameFrame extends JFrame {
    public final int X = 500;
    public final int Y = 200;
    public final int WIDTH = 1000;
    public final int HEIGHT = 650;
    public final int CENTER_X = WIDTH / 2;
    public final int CENTER_Y = HEIGHT / 2;


    public GameFrame() {
        setBounds(X, Y, WIDTH, HEIGHT);
        setLayout(null);
        setVisible(true);
    }
}

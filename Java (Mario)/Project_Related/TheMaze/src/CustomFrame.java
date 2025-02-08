import javax.swing.JFrame;

public class CustomFrame extends JFrame {

    public CustomFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(CustomPanel.WIDTH, CustomPanel.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}

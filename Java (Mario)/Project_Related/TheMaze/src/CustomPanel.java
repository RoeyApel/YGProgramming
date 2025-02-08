import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CustomPanel extends JPanel {

    public static final int HEIGHT = 900;
    public static final int WIDTH = 900;
    Controller controller;

    public CustomPanel(Controller controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        controller.render(g);
    }
}

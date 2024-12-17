import javax.swing.*;
import java.awt.*;

public class TryLayouts extends JFrame {
    private static final int R = 6;
    private static final int C = 6;

    public TryLayouts() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(R, C, 10, 10));

        Color bgColor, txtColor;

        for (int i = 0; i < R * C; i++) {
            JButton button = new JButton(i + 1 + "");

            int r = i / C, c = i % C;

            bgColor = ((r + c) % 2 == 0) ? Color.BLACK : Color.WHITE;
            txtColor = (bgColor == Color.BLACK) ? Color.WHITE : Color.BLACK;

            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(bgColor);
            button.setForeground(txtColor);
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);
        setSize(600, 600);
        setVisible(true);
    }

}
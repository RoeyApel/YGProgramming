import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuPanel extends JPanel {
    public JButton pvpButton;
    public JButton easyButton;
    public JButton hardButton;

    private BufferedImage background;

    public MenuPanel() {

        try {
            background = ImageIO.read(getClass().getResource("images\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("המבוך");
        title.setFont(new Font("Segoe UI", Font.BOLD, 70));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));

        pvpButton = createMenuButton("שחקן נגד שחקן");
        easyButton = createMenuButton("שחקן נגד מחשב (קל)");
        hardButton = createMenuButton("שחקן נגד מחשב (קשה)");

        add(title);
        add(pvpButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(easyButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(hardButton);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(400, 70));
        button.setMaximumSize(new Dimension(400, 70));
        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0, 120));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBorder(BorderFactory.createLineBorder(Color.decode("#fffbe0"), 2));
                button.setForeground(Color.decode("#fffbe0"));
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                button.setForeground(Color.WHITE);
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
    }
}

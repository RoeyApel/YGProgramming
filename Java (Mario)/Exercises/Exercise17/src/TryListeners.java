import javax.swing.*;
import java.awt.*;
import java.awt.desktop.AppHiddenEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TryListeners extends JFrame implements ActionListener {
    private static final String YELLOW = "yellow";
    private static final String GREEN = "green";
    private static final String RED = "red";
    private int num;
    private JButton r1, r2, r3;

    public TryListeners() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1, 20, 20));

        JPanel pr1 = new JPanel(new FlowLayout()), pr2 = new JPanel(new FlowLayout()), pr3 = new JPanel(
                new FlowLayout());

        panel2.add(pr1);
        panel2.add(pr2);
        panel2.add(pr3);

        add(panel, BorderLayout.NORTH);

        JButton button = new JButton(GREEN);
        button.addActionListener(this);
        panel.add(button);
        button = new JButton(YELLOW);
        button.addActionListener(this);
        panel.add(button);

        button = new JButton(RED);
        button.addActionListener(this);
        panel.add(button);

        add(panel2);
        r1 = new JButton();
        r1.setFont(new Font("Arial", Font.BOLD, 400));
        r1.setText(" ");
        pr1.add(r1);
        r2 = new JButton();
        r2.setFont(new Font("Arial", Font.BOLD, 400));
        r2.setText(" ");
        pr2.add(r2);
        r3 = new JButton();
        r3.setFont(new Font("Arial", Font.BOLD, 400));
        r3.setText(" ");
        pr3.add(r3);
        r1.setBackground(Color.BLACK);
        r2.setBackground(Color.BLACK);
        r3.setBackground(Color.BLACK);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JButton button = (JButton) e.getSource();
        if (action == YELLOW) {
            r1.setBackground(Color.BLACK);
            r2.setBackground(Color.YELLOW);
            r3.setBackground(Color.BLACK);
        } else if (action == GREEN) {
            r1.setBackground(Color.GREEN);
            r2.setBackground(Color.BLACK);
            r3.setBackground(Color.BLACK);
        } else if (action == RED) {
            r1.setBackground(Color.BLACK);
            r2.setBackground(Color.BLACK);
            r3.setBackground(Color.RED);
        }
    }
}

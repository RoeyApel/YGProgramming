import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class T2 extends JFrame implements ActionListener {
    private static final String BTN_TEXT = "play";
    private ArrayList<JButton> buttons;
    private JButton button;
    private int[] numbers;
    private ArrayList<JPanel> panels = new ArrayList<>();
    private JPanel myPanel = new JPanel();

    public T2() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(500, 400);

        buttons = new ArrayList<>();
        initNumbers();
        myPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            for (int j = 0; j < i + 1; j++) {
                JButton button = new JButton("" + i);
                buttons.add(button);
                panel.add(button);
            }
            panels.add(panel);
            myPanel.add(panel);
        }
        JPanel bPanel = new JPanel();
        button = new JButton(BTN_TEXT);
        button.addActionListener(this);
        bPanel.add(button);
        myPanel.add(bPanel);

        add(myPanel);

        // for (int i = 0; i < 6; i++) {
        // JButton b = new JButton("" + i + 1);
        // buttons.add(b);

        // }

    }

    private void initNumbers() {
        numbers = new int[49];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
    }

    private void mixNumbers() {
        int rndIndex, temp;
        for (int i = 0; i < numbers.length; i++) {
            rndIndex = (int) (Math.random() * 48);
            temp = numbers[rndIndex];
            numbers[rndIndex] = numbers[i];
            numbers[i] = temp;
        }
    }

    private void startLottery() {
        mixNumbers();

        for (int i = 0; i < 6; i++) {
            buttons.get(i).setText(numbers[i] + "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case BTN_TEXT:
                System.err.println("ssdgtdfgfgfgfdgfd");
                startLottery();
                break;

            default:
                break;
        }
    }
}
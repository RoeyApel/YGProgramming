import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class T1 extends JFrame implements ActionListener {
    private static final String BTN_TEXT = "play";
    private ArrayList<JButton> buttons;
    private JPanel leftPanel, centerPanel;
    private JButton button;
    private int[] numbers;

    public T1() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setSize(500, 400);

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 2));
        add(leftPanel, BorderLayout.WEST);

        buttons = new ArrayList<>();
        initNumbers();

        for (int i = 0; i < 6; i++) {
            JButton b = new JButton("" + i + 1);
            buttons.add(b);
            leftPanel.add(b);
        }
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 3));
        placeButtonCenter();
        add(centerPanel, BorderLayout.CENTER);
    }

    private void placeButtonCenter() {
        button = new JButton(BTN_TEXT);
        button.addActionListener(this);
        for (int i = 0; i < 8; i++) {
            if (i == 4) {
                centerPanel.add(button);
            }
            centerPanel.add(new JPanel());
        }
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
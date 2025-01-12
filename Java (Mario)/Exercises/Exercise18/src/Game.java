import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JOptionPane;

public class Game implements ActionListener {
    private RPanel panel;

    public Game() {
        RFrame frame = new RFrame();

        panel = new RPanel();
        frame.add(panel);

        JButton button = new JButton("Click Me");
        button.addActionListener(this);
        panel.add(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Click Me") {
            onMainButtonClick();
        }
    }

    private void onMainButtonClick() {
        String txtNumOfPieces = JOptionPane.showInputDialog(null, "Enter Num Of Pieces");
        int numOfPieces = tryParsingToInt(txtNumOfPieces);

        if (numOfPieces == -1) {
            return;
        }

        int sum = 0;
        int[] piecePortions = new int[numOfPieces];

        for (int i = 0; i < numOfPieces; i++) {
            String txtPortion = JOptionPane.showInputDialog(null, "Enter Portion:");
            int portion = tryParsingToInt(txtPortion);
            if (portion == -1) {
                return;
            }
            sum += portion;
            piecePortions[i] = portion;
        }
        panel.setVars(sum, numOfPieces, piecePortions);
        panel.repaint();

    }

    private int tryParsingToInt(String txt) {
        try {
            return Integer.parseInt(txt);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Error");
            return -1;
        }
    }
}

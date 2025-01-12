import java.awt.*;

import javax.swing.*;

public class RPanel extends JPanel {
    int sum;
    int numOfPieces;
    int[] piecePortions;
    int width, height, dx, dy;
    private Color[] colors = { Color.RED, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.BLACK };

    public RPanel() {
        sum = 0;
        numOfPieces = 0;
        piecePortions = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        width = getWidth();
        height = getHeight();
        dx = width / 10;
        dy = height / 10;

        if (numOfPieces > 0) {
            drawPie(g);
        }
    }

    private void drawPie(Graphics g) {
        int currentAngle = 0, angle = 0;

        for (int i = 0; i < numOfPieces; i++) {
            angle = piecePortions[i] * 360 / sum;
            g.setColor(colors[i]);
            g.fillArc(width / 2 - 4 * dx, height / 2 - 4 * dy, 8 * dx, 8 * dy, currentAngle, angle);
            currentAngle += angle;
        }
    }

    public void setVars(int sum, int numOfPieces, int[] piecePortions) {
        this.sum = sum;
        this.numOfPieces = numOfPieces;
        this.piecePortions = piecePortions;
    }
}

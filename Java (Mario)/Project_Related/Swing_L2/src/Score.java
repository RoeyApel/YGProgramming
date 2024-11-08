import java.awt.*;

public class Score {
    private int x, y;
    private Font font;
    private double score;

    public Score() {
        score = 0;
        font = new Font(Font.SERIF, Font.BOLD, 25);
        x = GameFrame.GAME_WIDTH / 2 -10;
        y = GameFrame.GAME_HEIGHT / 10;
    }

    public void draw(Graphics g, boolean gameOver) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.black);
        g.setFont(font);

        if (gameOver) {
            g.drawString("Game Over (" + getScoreTxt() + ")", x - 75, y);
        } else {
            g.drawString(getScoreTxt(), x, y);
        }
    }

    public void increment() {
        score += 0.5;
    }

    public double getScore() {
        return score;
    }

    public String getScoreTxt() {
        return String.valueOf((int) score);
    }
}

import java.awt.*;

public class Slot extends Piece {
    public static final int NONE = -1;
    public static final int NORMAL_SLOT = 4;
    public static final int WINNING_SLOT = 5;

    private int kind;
    private int playerNum;

    public Slot(int id, int status) {
        this.id = id;
        this.kind = status;
        this.playerNum = -1;
    }

    public void draw(Graphics g) {
        if (kind == NORMAL_SLOT) {
            g.setColor(new Color(239, 222, 205));
            g.fillRect(x, y, width, height);
        } else if (kind == WINNING_SLOT) {
            g.setColor(new Color(119, 202, 205));
            g.fillRect(x, y, width, height);
        }

        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.drawString(id + "", x + width / 2, y + height / 2);
    }
}

import java.awt.*;

public class Slot {
    public static final int NONE = -1;
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int NORMAL_SLOT = 2;
    public static final int WINNING_SLOT = 3;

    private int x, y;
    private int width, height;
    private int slotNum;
    private int kind;
    private int leftWall;
    private int buttomWall;

    public Slot() {

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
        g.drawString(slotNum + "", x + width / 2, y + height / 2);
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

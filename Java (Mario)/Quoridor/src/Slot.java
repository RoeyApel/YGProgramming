import java.awt.*;

public class Slot {
    public static final int NONE = -1;
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int NORMAL_SLOT = 2;
    public static final int WINNING_SLOT = 3;

    private Rectangle hitbox;
    private int slotId;
    private int row, col;
    private int kind;
    private int left;
    private int buttom;

    public Slot(int row, int col, int kind) {
        this.kind = kind;
        this.row = row;
        this.col = col;
        slotId = row * Board.COLS + col;

        buttom = (row == Board.ROWS - 1) ? NONE : EMPTY;
        left = (col == Board.COLS - 1) ? NONE : EMPTY;

        hitbox = new Rectangle();
    }

    public void draw(Graphics g) {
        if (kind == NORMAL_SLOT) {
            g.setColor(new Color(239, 222, 205));
            g.drawImage(Slots.NORMAL.getSlotImage(), hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
        } else if (kind == WINNING_SLOT) {
            g.setColor(new Color(119, 202, 205));
            g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }

        g.setColor(Color.black);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        g.drawString(slotId + "", hitbox.x + hitbox.width / 2, hitbox.y + hitbox.height / 2);
    }

    public void setBounds(int x, int y, int width, int height) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotNum) {
        this.slotId = slotNum;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int leftWall) {
        this.left = leftWall;
    }

    public int getButtom() {
        return buttom;
    }

    public void setButtom(int buttomWall) {
        this.buttom = buttomWall;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

}

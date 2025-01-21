import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Character {
    private Rectangle hitbox;
    private int row, col;
    private int slotId;
    private Image image;

    public Character(Image image, int row, int col) {
        this.image = image;
        this.row = row;
        this.col = col;
        slotId = col + row * Board.COLS;
        hitbox = new Rectangle();
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        System.out.println(hitbox);
    }

    public void setBounds(int x, int y, int width, int height) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
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

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotNum) {
        this.slotId = slotNum;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}

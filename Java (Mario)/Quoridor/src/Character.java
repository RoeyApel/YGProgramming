import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Character {
    private Rectangle hitbox;
    private Position position;
    private Image image;

    public Character(Image image, int row, int col) {
        this.image = image;
        position.row = row;
        position.col = col;
        hitbox = new Rectangle();
    }

    public void draw(Graphics g) {
        g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
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
        return position.row;
    }

    public void setRow(int row) {
        position.row = row;
    }

    public int getCol() {
        return position.col;
    }

    public void setCol(int col) {
        position.col = col;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}

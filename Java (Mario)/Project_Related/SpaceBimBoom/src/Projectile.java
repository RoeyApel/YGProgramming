import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Projectile {
    public static final int GOOD_SPEED = 15;
    public static final int BAD_SPEED = 20;

    private static final int WIDTH = 10;
    private static final int HEIGHT = 30;

    private int x, y;
    private int speed;
    private Directions direction;
    private Image image;
    private Rectangle hitbox;

    public Projectile(Image image, int x, int y, Directions direction, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.image = image;
        this.hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public void update() {
        move();
    }

    private void move() {
        int moveBy = direction == Directions.UP ? -1 * speed : 1 * speed;
        y += moveBy;
        hitbox.y += moveBy;
    }

    public boolean exitedScreen() {
        return y + HEIGHT <= 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

}

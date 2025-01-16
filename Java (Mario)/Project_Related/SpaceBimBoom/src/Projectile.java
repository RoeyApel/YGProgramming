import java.awt.Graphics;
import java.awt.Image;

public class Projectile {
    public static final int GOOD_SPEED = 30;
    public static final int BAD_SPEED = 30;

    private static final int WIDTH = 10;
    private static final int HEIGHT = 30;

    private int x, y;
    private int speed;
    private Directions direction;
    private Image image;

    public Projectile(Image image, int x, int y, Directions direction, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.image = image;
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
    }

    public boolean outOfScreen() {
        return y + HEIGHT <= 0;
    }
}

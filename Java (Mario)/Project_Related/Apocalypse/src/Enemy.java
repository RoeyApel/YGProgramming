import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Enemy {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;
    public static final int MIN_SPEED = 3;
    public static final int MAX_SPEED = 6;

    private Rectangle hitbox;
    private double angle;
    private int speed;
    private AffineTransform transform;
    private Image image;

    public Enemy(int x, int y, int speed) {
        transform = new AffineTransform();
        hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
        image = Images.ENEMY.getImage(hitbox.width, hitbox.height);
        this.speed = speed;
    }

    public void draw(Graphics g) {
        drawEnemy(g);
        // drawHitbox(g);
    }

    private void drawEnemy(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        transform.setToIdentity();

        transform.translate(hitbox.getCenterX(), hitbox.getCenterY());

        transform.rotate(Math.toRadians(angle));

        transform.translate(-hitbox.width / 2.0, -hitbox.height / 2.0);

        g2d.drawImage(image, transform, null);
    }

    public void update() {
        double dirX = Math.cos(Math.toRadians(angle));
        double dirY = Math.sin(Math.toRadians(angle));

        move(dirX * speed, dirY * speed);
    }

    private void move(double moveX, double moveY) {
        hitbox.x += moveX;
        hitbox.y += moveY;
    }

    public void setAngleToward(Rectangle other) {
        double dirX = other.getCenterX() - hitbox.x;
        double dirY = other.getCenterY() - hitbox.y;

        this.angle = Math.toDegrees(Math.atan2(dirY, dirX));
    }

    public Bullet getCollisionBullet(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            if (hitbox.intersects(bullet.getHitbox())) {
                return bullet;
            }
        }
        return null;
    }

    public boolean collidedWithPlayer(Player player) {
        return hitbox.intersects(player.getHitbox());
    }
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Bullet {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    private static final int SPEED = 25;

    private Rectangle hitbox;
    private double angle;
    private AffineTransform transform;
    private Image image;

    public Bullet(int x, int y, double angle) {
        this.angle = angle;
        transform = new AffineTransform();
        hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
        image = Images.BULLET.getImage(hitbox.width, hitbox.height);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        transform.setToIdentity();

        transform.translate(hitbox.getCenterX(), hitbox.getCenterY());

        transform.rotate(Math.toRadians(angle));

        transform.translate(-hitbox.width / 2.0, -hitbox.height / 2.0);

        g2d.drawImage(image, transform, null);

        // drawHitbox(g);
    }

    public void update() {
        double dirX = Math.cos(Math.toRadians(angle - 90));
        double dirY = Math.sin(Math.toRadians(angle - 90));

        move(dirX * SPEED, dirY * SPEED);
    }

    private void move(double moveX, double moveY) {
        hitbox.x += moveX;
        hitbox.y += moveY;
    }

    public boolean isOutOfScreen() {
        return hitbox.x + hitbox.width <= 0 || hitbox.x >= GamePanel.WIDTH || hitbox.y + hitbox.height <= 0
                || hitbox.y >= GamePanel.HEIGHT;
    }

    private void drawHitbox(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}

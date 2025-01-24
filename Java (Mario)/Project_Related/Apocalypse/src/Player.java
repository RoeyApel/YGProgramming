import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 150;
    public static final int SHOOTING_COOLDOWN = 300;

    private Rectangle hitbox;
    private double angle;
    private AffineTransform transform;
    private Image image;
    private ArrayList<Bullet> bullets;

    public Player(int x, int y) {
        transform = new AffineTransform();
        hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
        image = Images.PLAYER.getImage(hitbox.width, hitbox.height);
        bullets = new ArrayList<>();

    }

    public void draw(Graphics g) {
        drawBullets(g);
        drawPlayer(g);
        // drawHitbox(g);
    }

    public void update() {
        updateBullets();
    }

    private synchronized void drawBullets(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    private synchronized void updateBullets() {
        bullets.removeIf((bullet) -> {
            if (bullet.isOutOfScreen())
                return true;

            bullet.update();
            return false;
        });
    }

    private void drawHitbox(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void drawPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        transform.setToIdentity();

        transform.translate(hitbox.getCenterX(), hitbox.getCenterY());

        transform.rotate(Math.toRadians(angle));

        transform.translate(-hitbox.width / 2.0, -hitbox.height / 2.0);

        g2d.drawImage(image, transform, null);
    }

    public synchronized void shot() {
        Bullet bullet = new Bullet(hitbox.x + Bullet.WIDTH / 4, hitbox.y + Bullet.HEIGHT / 4, angle);
        bullets.add(bullet);
    }

    public Point getPosition() {
        return hitbox.getLocation();
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void removeBullet(Bullet hittingBullet) {
        bullets.remove(hittingBullet);
    }
}

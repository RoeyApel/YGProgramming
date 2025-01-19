import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class EnemyShip {
    private static final int INITIAL_SPEED = 4;
    private static final int SHOTTING_INTERVAL = 45;

    private int x, y, centerX;
    private int width;
    private int height;
    private int speed;
    private double angle;
    private Rectangle hitbox;
    private Image image;

    private ArrayList<Projectile> projectiles;

    private static AffineTransform transform = new AffineTransform();

    private int tick;

    public EnemyShip(int x, int width, int height) {
        this.x = x;
        this.y = -height;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(x, y, width, height);

        centerX = x + width / 2 - 5;
        speed = INITIAL_SPEED;
        angle = 180;

        projectiles = new ArrayList<>();

        image = Images.BAD_SHIP.getImage(width, height);

        tick = 0;
    }

    public void draw(Graphics g) {
        synchronized (projectiles) {
            for (Projectile projectile : projectiles) {
                projectile.draw(g);
            }
        }

        drawShip(g);
        drawHitbox(g);
    }

    private void drawShip(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        transform.setToIdentity();

        transform.translate(x + width / 2.0, y + height / 2.0);

        transform.rotate(Math.toRadians(angle));

        transform.translate(-width / 2.0, -height / 2.0);

        g2d.drawImage(image, transform, null);
    }

    public void update() {
        move();

        if (tick >= SHOTTING_INTERVAL) {
            shot();
            tick = 0;
        } else {
            tick++;
        }

        synchronized (projectiles) {
            for (Projectile projectile : projectiles) {
                projectile.update();
            }
        }
    }

    private void move() {
        y += speed;
        hitbox.y += speed;
    }

    public void shot() {
        Projectile projectile = new Projectile(Images.BAD_PROJECTILE.getImage(), centerX, y, Directions.DOWN,
                Projectile.BAD_SPEED);
        projectiles.add(projectile);
    }

    private void drawHitbox(Graphics g) {
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public boolean exitedScreen() {
        return y > GamePanel.HEIGHT;
    }

    public boolean colidedWithProjectile(PlayerShip playerShip) {
        if (playerShip.numOfProjectiles() == 0)
            return false;

        for (Projectile projectile : playerShip.getProjectiles()) {
            if (hitbox.intersects(projectile.getHitbox())) {
                return true;
            }
        }
        return false;
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

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

}

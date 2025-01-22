import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class PlayerShip {
    private static final int INITIAL_SPEED = 13;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int INITIAL_X = GamePanel.WIDTH / 2 - WIDTH / 2;
    private static final int Y = GamePanel.HEIGHT - HEIGHT;
    private static final int HITBOX_SCALE_X = 30;
    private static final int INVINCIBILITY_DURATION = 2000;
    private static final int BLINK_INTERVAL = 100;

    private Rectangle hitbox;
    private int x, centerX;
    private int speed;
    private int healthPoints;
    private HashSet<Directions> moves;
    private ArrayList<Projectile> projectiles;
    private boolean invincible, visible;
    private long invincibilityEndTime, lastBlinkTime;

    public PlayerShip() {
        this.x = INITIAL_X;
        centerX = x + WIDTH / 2 - 5;
        hitbox = new Rectangle(x + HITBOX_SCALE_X / 2, Y, WIDTH - HITBOX_SCALE_X, HEIGHT);

        speed = INITIAL_SPEED;
        healthPoints = 3;

        invincible = false;
        visible = true;

        invincibilityEndTime = 0;
        lastBlinkTime = 0;

        moves = new HashSet<>();
        projectiles = new ArrayList<>();
    }

    public void draw(Graphics g) {
        drawProjectiles(g);

        if (visible) {
            g.drawImage(Images.GOOD_SHIP.getImage(), x, Y, WIDTH, HEIGHT, null);
            // drawHitbox(g);
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        if (moves.contains(Directions.LEFT) && !moves.contains(Directions.RIGHT)) {
            move(Directions.LEFT);
        } else if (moves.contains(Directions.RIGHT) && !moves.contains(Directions.LEFT)) {
            move(Directions.RIGHT);
        }

        updateProjectiles();

        if (invincible) {

            if (currentTime >= invincibilityEndTime) {
                invincible = false;
                visible = true;
                return;
            }

            if (currentTime - lastBlinkTime >= BLINK_INTERVAL) {
                visible = !visible;
                lastBlinkTime += BLINK_INTERVAL;
            }
        }
    }

    public void shot() {
        Projectile projectile = new Projectile(Images.GOOD_PROJECTILE.getImage(), centerX, Y, Directions.UP,
                Projectile.GOOD_SPEED);
        projectiles.add(projectile);
    }

    private void updateProjectiles() {
        projectiles.removeIf(projectile -> {
            if (projectile.exitedScreen())
                return true;

            projectile.update();
            return false;
        });
    }

    private void drawProjectiles(Graphics g) {
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

    private void drawHitbox(Graphics g) {
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void move(Directions direction) {
        int moveBy = direction == Directions.LEFT ? -1 * speed : 1 * speed;

        if (x + moveBy > 0 && x + WIDTH + moveBy < GamePanel.WIDTH) {
            x += moveBy;
            centerX += moveBy;
            hitbox.x += moveBy;
        }
    }

    public void addMove(Directions direction) {
        moves.add(direction);
    }

    public void removeMove(Directions direction) {
        moves.remove(direction);
    }

    public void onHit(int healthLost) {
        if (!invincible) {
            healthPoints = Math.max(0, healthPoints - healthLost);
            enterInvincibility();
        }
    }

    private void enterInvincibility() {
        invincible = true;
        invincibilityEndTime = System.currentTimeMillis() + INVINCIBILITY_DURATION;
        lastBlinkTime = System.currentTimeMillis();
    }

    public synchronized boolean colidedWithEnemy(ArrayList<EnemyShip> enemyShips) {
        for (EnemyShip enemyShip : enemyShips) {
            if (hitbox.intersects(enemyShip.getHitbox())) {
                return true;
            }
        }
        return false;
    }

    public boolean colidedWithEnemyProjectile(ArrayList<EnemyShip> enemyShips) {
        for (EnemyShip enemyShip : enemyShips) {
            for (Projectile projectile : enemyShip.getProjectiles()) {
                if (hitbox.intersects(projectile.getHitbox())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int numOfProjectiles() {
        return projectiles.size();
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

}

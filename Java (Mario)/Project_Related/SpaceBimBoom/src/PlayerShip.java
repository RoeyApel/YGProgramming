import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashSet;

public class PlayerShip {
    private static final int INITIAL_SPEED = 10;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int INITIAL_X = GamePanel.WIDTH / 2 - WIDTH / 2;
    private static final int Y = GamePanel.HEIGHT - HEIGHT;
    private static final int HITBOX_SCALE_X = 30;

    private Rectangle hitbox;
    private int x;
    private int speed;
    private HashSet<Directions> moves;

    public PlayerShip() {
        this.x = INITIAL_X;
        hitbox = new Rectangle(x + HITBOX_SCALE_X / 2, Y, WIDTH - HITBOX_SCALE_X, HEIGHT);
        speed = INITIAL_SPEED;
        moves = new HashSet<>();
    }

    public void draw(Graphics g) {
        g.drawImage(Images.GOOD_SHIP.getImage(), x, Y, WIDTH, HEIGHT, null);
        drawHitbox(g);
    }

    public void update() {
        if (moves.contains(Directions.LEFT) && !moves.contains(Directions.RIGHT)) {
            move(Directions.LEFT);
        } else if (moves.contains(Directions.RIGHT) && !moves.contains(Directions.LEFT)) {
            move(Directions.RIGHT);
        }

    }

    private void drawHitbox(Graphics g) {
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void move(Directions direction) {
        int moveBy = direction == Directions.LEFT ? -1 * speed : 1 * speed;
        x += moveBy;
        hitbox.x += moveBy;
    }

    public void addMove(Directions direction) {
        moves.add(direction);
    }

    public void removeMove(Directions direction) {
        moves.remove(direction);
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

}

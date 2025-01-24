import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {
    private Random random = new Random();
    private static final long SPAWN_INTERVAL = 600;
    private long endSpawnCooldown = 0;
    private int[] places = { 0, GamePanel.WIDTH, 0, GamePanel.HEIGHT };
    private Playing playing;

    public EnemySpawner(Playing playing) {
        this.playing = playing;
    }

    public void spawn(long currentTime) {
        Point position = getRandomPosition();
        int speed = random.nextInt(Enemy.MIN_SPEED, Enemy.MAX_SPEED + 1);

        if (currentTime >= endSpawnCooldown) {
            spawn(position, speed);
            endSpawnCooldown = currentTime + SPAWN_INTERVAL;
        }
    }

    private void spawn(Point position, int speed) {
        Enemy enemy = new Enemy(position.x, position.y, speed);

        enemy.setAngleToward(playing.getPlayer().getHitbox());
        playing.addEnemy(enemy);
    }

    private Point getRandomPosition() {
        int place = random.nextInt(4);
        int horizontalRange = random.nextInt(0, GamePanel.WIDTH);
        int verticalRange = random.nextInt(0, GamePanel.WIDTH);

        int x, y;

        if (place == 0) {
            x = -Enemy.WIDTH;
            y = horizontalRange;
        } else if (place == 1) {
            x = GamePanel.WIDTH;
            y = horizontalRange;
        } else if (place == 2) {
            x = verticalRange;
            y = -Enemy.HEIGHT;
        } else {
            x = verticalRange;
            y = GamePanel.HEIGHT;
        }

        return new Point(x, y);
    }
}

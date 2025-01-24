import java.awt.Graphics;
import java.util.ArrayList;

public class Playing implements GameState {
    private Game game;
    private Mouse mouse;
    private Player player;
    private EnemySpawner enemySpawner;
    private ArrayList<Enemy> enemies;
    private long endShootingCooldown = 0;
    private boolean isGameOver;

    public Playing(Game game) {
        this.game = game;
        this.mouse = game.getMouse();
        int centerX = GamePanel.WIDTH / 2 - Player.WIDTH / 2;
        int centerY = GamePanel.HEIGHT / 2 - Player.HEIGHT / 2;
        player = new Player(centerX, centerY);
        enemySpawner = new EnemySpawner(this);
        enemies = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        player.draw(g);

        drawEnemies(g);

        game.drawScore(g, "top");
    }

    private void drawTrangle(Graphics g) {
        int centerX = (int) player.getHitbox().getCenterX();
        int centerY = (int) player.getHitbox().getCenterY();
        g.drawLine(centerX, centerY, mouse.x, mouse.y);
        g.drawLine(centerX, centerY, mouse.x, centerY);
        g.drawLine(mouse.x, centerY, mouse.x, mouse.y);
    }

    private synchronized void drawEnemies(Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    @Override
    public void update() {
        if (isGameOver) {
            game.setGameState(Game.GameStates.MENU);
        }

        long currentTime = System.currentTimeMillis();

        player.update();

        double a = mouse.x - player.getHitbox().getCenterX();
        double b = mouse.y - player.getHitbox().getCenterY();

        double playerAngle = Math.toDegrees(Math.atan2(b, a)) + 90;

        player.setAngle(playerAngle);

        if (mouse.pressed) {
            if (currentTime >= endShootingCooldown) {
                player.shot();
                endShootingCooldown = currentTime + Player.SHOOTING_COOLDOWN;
            }
        }

        enemySpawner.spawn(currentTime);

        updateEnemies();
    }

    private synchronized void updateEnemies() {
        enemies.removeIf((enemy) -> {
            if (enemy.collidedWithPlayer(player)) {
                isGameOver = true;
                return true;
            }

            Bullet hittingBullet = enemy.getCollisionBullet(player.getBullets());
            if (hittingBullet != null) {
                player.removeBullet(hittingBullet);
                game.increaseScore(1);
                return true;
            }

            enemy.update();
            return false;
        });
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Player getPlayer() {
        return player;
    }

}

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Playing {
    private Game game;
    private PlayerShip playerShip;
    private SpawnManager spawnManager;
    private ArrayList<EnemyShip> enemyShips;
    private UIComponents uiComponents;

    public Playing(Game game) {
        this.game = game;

        uiComponents = new UIComponents();

        playerShip = new PlayerShip();
        enemyShips = new ArrayList<>();
        spawnManager = new SpawnManager(this);

        spawnManager.startSpawning();
    }

    public void render(Graphics g) {
        playerShip.draw(g);

        synchronized (enemyShips) {
            for (EnemyShip enemyShip : enemyShips) {
                enemyShip.draw(g);
            }
        }

        uiComponents.drawHearts(g, playerShip.getHealthPoints());
    }

    public void update() {
        playerShip.update();

        if (playerShip.colidedWithEnemy(enemyShips)) {
            playerShip.onHit(2);
        } else if (playerShip.colidedWithEnemyProjectile(enemyShips)) {
            playerShip.onHit(1);
        }

        if (playerShip.isDead()) {
            gameOver();
        }

        synchronized (enemyShips) {
            enemyShips.removeIf(enemyShip -> {
                if (enemyShip.exitedScreen())
                    return true;

                if (enemyShip.colidedWithProjectile(playerShip))
                    return true;

                enemyShip.update();
                return false;
            });
        }
    }

    public void gameOver() {
        spawnManager.stopSpawning();
        reset();
        game.setGameState(Game.GameStates.MENU);
    }

    private void reset() {
        playerShip = new PlayerShip();
        enemyShips = new ArrayList<>();
    }

    public void restart() {
        spawnManager.startSpawning();
    }

    public void onKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            playerShip.addMove(Directions.LEFT);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            playerShip.addMove(Directions.RIGHT);
        }
    }

    public void onKeyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            playerShip.shot();
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            playerShip.removeMove(Directions.LEFT);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            playerShip.removeMove(Directions.RIGHT);
        }
    }

    public synchronized void addEnemyShip(EnemyShip enemyShip) {
        enemyShips.add(enemyShip);
    }

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

}

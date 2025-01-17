import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SpawnManager implements ActionListener {
    private static final int SPAWN_INTERVAL = 1500;

    private Timer spawnTimer;
    private Playing playing;

    public SpawnManager(Playing playing) {
        this.playing = playing;
        spawnTimer = new Timer(SPAWN_INTERVAL, this);
    }

    public void startSpawning() {
        spawnTimer.start();
    }

    public void stopSpawning() {
        spawnTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spawn();
    }

    private void spawn() {
        int width = 100, height = 100;
        int x = getRndXPos(0, GamePanel.WIDTH - width);

        EnemyShip enemyShip = new EnemyShip(x, width, height);
        playing.addEnemyShip(enemyShip);
    }

    private int getRndXPos(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}

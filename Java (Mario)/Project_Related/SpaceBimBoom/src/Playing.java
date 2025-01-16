import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Playing {
    private PlayerShip playerShip;

    public Playing() {
        playerShip = new PlayerShip();
    }

    public void render(Graphics g) {
        playerShip.draw(g);
    }

    public void update() {
        playerShip.update();
    }

    public void onKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            playerShip.addMove(Directions.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            playerShip.addMove(Directions.RIGHT);
        }
    }

    public void onKeyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            playerShip.removeMove(Directions.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            playerShip.removeMove(Directions.RIGHT);
        }
    }
}

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
}

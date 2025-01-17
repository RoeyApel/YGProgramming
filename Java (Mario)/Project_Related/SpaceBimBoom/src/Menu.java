import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Menu {
    private Game game;

    public Menu(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        g.drawImage(Images.GOOD_SHIP.getImage(), GamePanel.WIDTH / 2 - Images.GOOD_SHIP.getWidth() / 2, 200, null);

        g.setColor(Color.white);
        g.drawString("menu hi", GamePanel.WIDTH / 2 - Images.GOOD_SHIP.getWidth() / 2, Images.GOOD_SHIP.getHeight());
    }

    public void update() {

    }

    public void onKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            game.restart();
        }
    }

    public void onKeyReleased(KeyEvent e) {
    }
}

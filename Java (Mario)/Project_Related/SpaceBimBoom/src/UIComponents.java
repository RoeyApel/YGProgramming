import java.awt.Graphics;
import java.awt.Image;

public class UIComponents {

    public UIComponents() {
    }

    public void drawHearts(Graphics g, int healthPoints) {
        int x = 15, y = 15;
        int width = Images.HEART.getWidth() * 2, height = Images.HEART.getHeight() * 2;

        for (int i = 0; i < healthPoints; i++) {
            g.drawImage(Images.HEART.getImage(), x, y + i * (height + 6), width, height, null);
        }
    }
}

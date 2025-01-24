import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public enum Images {
    PLAYER("images\\player.png"), ENEMY("images\\enemy.png"), BULLET("images\\bullet.png"), SPACE("images\\space.png");

    private Image image;

    Images(String path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource(path));
            this.image = bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getImage(int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public Image getImage() {
        return image;
    }
}

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Images {
    BACKGROUND("images/space.png"),
    BAD_PROJECTILE("images/bad_projectile.png"),
    GOOD_PROJECTILE("images/good_projectile.png"),
    BAD_SHIP("images/bad_ship.png"),
    GOOD_SHIP("images/good_ship.png"),
    BAD_TORPEDO("images/bad_torpedo.png"),
    HEART("images/heart.png");

    private Image image;

    Images(String path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource(path));
            this.image = bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public Image getImage() {
        return image;
    }

    public Image getImage(int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }
}

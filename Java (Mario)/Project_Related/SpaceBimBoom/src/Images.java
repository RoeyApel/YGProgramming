import java.awt.Image;

import javax.swing.ImageIcon;

public enum Images {
    BACKGROUND("images\\space.png"), BAD_PROJECTILE("images\\bad_projectile.png"),
    GOOD_PROJECTILE("images\\good_projectile.png"), BAD_SHIP("images\\bad_ship.png"),
    GOOD_SHIP("images\\good_ship.png"), BAD_TORPEDO("images\\bad_torpedo.png"),;

    Image image;

    Images(String path) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    public Image getImage() {
        return image;
    }
}

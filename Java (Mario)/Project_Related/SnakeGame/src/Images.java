import java.awt.Image;

import javax.swing.ImageIcon;

public enum Images {
    APPLE("images/apple.png");

    private Image image;

    Images(String path) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    public Image getImage() {
        return image;
    }

}

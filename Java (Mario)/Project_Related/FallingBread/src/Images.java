import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Images {
    BREAD("images\\bread.png"), TOASTER("images\\toaster.png");

    Image image;

    Images(String path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            image = bufferedImage;
        } catch (IOException e) {
            image = null;
            System.out.println("image load fail");
        }
    }

    public Image getImage(int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

}

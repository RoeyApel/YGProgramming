import java.awt.Image;
import javax.swing.ImageIcon;

public enum Images {
    BACKGROUND("images/flappy_bird_bg.png", GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT),
    BIRD("images/flappy_bird.png", 34, 24),
    TOP_PIPE("images/top_pipe.png", 64, 512),
    BUTTON_PIPE("images/bottom_pipe.png", 64, 512);

    private Image image;
    private int width, height;

    Images(String path, int width, int height) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
        this.width = width;
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

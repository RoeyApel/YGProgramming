import java.awt.Image;

import javax.swing.ImageIcon;

public enum Pieces {
    PLAYER("images/checker1.svg", 60),
    OPPONENT("images/checker2.svg", 60),
    PLAYER_KING("images/checker1.svg", 70),
    OPPONENT_KING("images/checker2.svg", 70);

    private Image image;
    private int size;

    Pieces(String path, int size) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
        image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        this.size = size;
    }

    public Image getImage() {
        return image;
    }

    public int getSize() {
        return size;
    }
}

import java.awt.Image;

import javax.swing.ImageIcon;

public enum Characters {
    PLAYER("images\\player.png", (Board.ROWS - 1) * Board.COLS + Board.COLS / 2),
    OPPONENT("images\\player.png", Board.COLS / 2);

    private Image image;

    private int startSlotId;

    Characters(String path, int startSlotId) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
        this.startSlotId = startSlotId;
    }

    public Image getImage() {
        return image;
    }

    public int getStartSlotId() {
        return startSlotId;
    }
}

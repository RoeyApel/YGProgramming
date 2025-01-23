import java.awt.Image;

import javax.swing.ImageIcon;

public enum Walls {
    NONE("", ""),
    EMPTY("", ""),
    WALL("images\\horizontal_wall.png",
            "images\\vertical_wall.png"),
    SELECTED_WALL("images\\horizontal_wall_selected.png", "images\\vertical_wall_selected.png");

    private Image horizontal, vertical;

    Walls(String horizontalPath, String verticalPath) {
        horizontal = new ImageIcon(getClass().getResource(horizontalPath)).getImage();
        vertical = new ImageIcon(getClass().getResource(verticalPath)).getImage();
    }

    public Image getHorizontalImg() {
        return horizontal;
    }

    public Image getVerticalImg() {
        return vertical;
    }
}

import java.awt.Image;

import javax.swing.ImageIcon;

public enum Characters {
    PLAYER("images\\player_down.png",
            "images\\player_left.png",
            "images\\player_right.png",
            "images\\player_up.png");

    Image sprite_down, sprite_left, sprite_right, sprite_up;

    Characters(String down, String left, String right, String up) {
        sprite_down = new ImageIcon(getClass().getResource(down)).getImage();
        sprite_left = new ImageIcon(getClass().getResource(left)).getImage();
        sprite_right = new ImageIcon(getClass().getResource(right)).getImage();
        sprite_up = new ImageIcon(getClass().getResource(up)).getImage();
    }

    public Image getImage(Directions direction) {
        switch (direction) {
            case DOWN:
                return sprite_down;
            case LEFT:
                return sprite_left;
            case RIGHT:
                return sprite_right;
            case UP:
                return sprite_up;
            default:
                return null;
        }
    }
}

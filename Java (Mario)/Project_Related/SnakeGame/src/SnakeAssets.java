import java.awt.Image;

import javax.swing.ImageIcon;

public enum SnakeAssets {
    SNAKE("images/head_up.png", "images/head_down.png", "images/head_left.png", "images/head_right.png",
            "images/snake_body.png");

    private Image head_down, head_left, head_up, head_right, body;

    SnakeAssets(String headUpURL, String headDownURL, String headLeftURL, String headRightURL, String bodyURL) {
        head_down = new ImageIcon(getClass().getResource(headDownURL)).getImage();
        head_up = new ImageIcon(getClass().getResource(headUpURL)).getImage();
        head_left = new ImageIcon(getClass().getResource(headLeftURL)).getImage();
        head_right = new ImageIcon(getClass().getResource(headRightURL)).getImage();
        body = new ImageIcon(getClass().getResource(bodyURL)).getImage();
    }

    public Image getBody() {
        return body;
    }

    public Image getHead(Directions direction) {
        switch (direction) {
            case UP:
                return head_up;

            case DOWN:
                return head_down;

            case LEFT:
                return head_left;

            case RIGHT:
                return head_right;
        }
        return null;
    }
}

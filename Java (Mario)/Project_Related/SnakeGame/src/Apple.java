import java.awt.*;

import javax.swing.ImageIcon;

public class Apple {
    private int row, col;
    private int x, y;
    private int width, height;
    private Color color;
    private Image image;

    public Apple(int row, int col) {
        this.row = row;
        this.col = col;
        color = new Color(200, 20, 30);
    }

    public void draw(Graphics g, int dx, int dy) {
        this.x = col * dx;
        this.y = row * dy;
        this.width = dx;
        this.height = dy;
        this.image = Images.APPLE.getImage();

        g.drawImage(image, x, y, width, height, null);
        // g.setColor(color);
        // g.fillOval(x, y, width, height);

        // g.setColor(new Color(240, 50, 80));
        // g.drawOval(x, y, width, height);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

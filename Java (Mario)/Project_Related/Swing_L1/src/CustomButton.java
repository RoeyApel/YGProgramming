import javax.swing.JButton;

public class CustomButton extends JButton {
    private int x, y, centerX, centerY;
    private int width, height;

    public CustomButton(int x, int y, int width, int height, String text) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setBounds(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        setLocation(x, y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        setLocation(x, y);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        setSize(width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        setSize(width, height);
    }

    public int getCenterX() {
        return width / 2;
    }

    public int getCenterY() {
        return height / 2;
    }

}

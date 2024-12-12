import java.io.ObjectInputStream.GetField;

import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final int SPEEDX = 20;
    public static final int SPEEDY = 20;

    public static int screenWidth;
    public static int screenHeight;

    int speedX = SPEEDX;
    int speedY = SPEEDY;

    public GameFrame(String title, int x, int y) {
        super(title);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        screenWidth = d.width;
        screenHeight = d.height;

        this.setSize(screenWidth / 5, screenHeight / 4);
        this.setLocation(x, y);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void move() {
        int newPosX = getX() + speedX;
        int newPosY = getY() + speedY;

        if (newPosX < 0) {
            speedX *= -1;
        } else if (newPosX + getWidth() > screenWidth) {
            speedX *= -1;
        } else if (newPosY < 0) {
            speedY *= -1;
        } else if (newPosY + getHeight() > screenHeight) {
            speedY *= -1;
        }
        this.setLocation(newPosX, newPosY);
    }

    public void changeSpeed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void handleCollisions(GameFrame other) {
        if (getX() < other.getX() && getX() + getWidth() > other.getX()
                || getX() > other.getX() && getX() + getWidth() < other.getX()) {
            this.speedX *= -1;
            other.speedX *= -1;
        }
    }
}

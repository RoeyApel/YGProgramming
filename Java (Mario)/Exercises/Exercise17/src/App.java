import java.awt.Dimension;
import java.awt.Toolkit;

public class App {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame("Game", 300, 300);
        GameFrame gameFrame2 = new GameFrame("Game", 1000, 600);

        while (true) {

            gameFrame.move();
            gameFrame2.move();
            gameFrame2.handleCollisions(gameFrame);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

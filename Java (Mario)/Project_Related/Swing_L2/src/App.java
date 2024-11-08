import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {

        GameFrame gameFrame = new GameFrame();

        FlappyBird flappyBird = new FlappyBird();

        gameFrame.add(flappyBird);
        gameFrame.pack();
        gameFrame.requestFocus();
        gameFrame.setVisible(true);
    }
}

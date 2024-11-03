import javax.swing.JButton;

public class Game {
    GameFrame gameFrame;
    CustomButton button;

    public Game() {
        gameFrame = new GameFrame();
        initComponents();
        addComponentsToFrame();
    }

    public void initComponents() {
        button = new CustomButton(gameFrame.CENTER_X - 50, gameFrame.CENTER_Y - 25, 100, 50, "Kill me");
        button.setBounds(3, 3, button.getWidth(), button.getHeight());
    }

    public void addComponentsToFrame() {
        gameFrame.add(button);
    }
}

import java.awt.BufferCapabilities.FlipContents;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameLoop implements ActionListener {
    private static final int DELAY_MILLIS = 1000 / 60;

    private Timer gameLoop;
    private FlappyBird flappyBird;

    public GameLoop(FlappyBird flappyBird) {
        gameLoop = new Timer(DELAY_MILLIS, this);
        this.flappyBird = flappyBird;
    }

    public void startLoop() {
        gameLoop.start();
    }

    public void stopLoop() {
        gameLoop.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        flappyBird.update();
        flappyBird.repaint();
    }
}

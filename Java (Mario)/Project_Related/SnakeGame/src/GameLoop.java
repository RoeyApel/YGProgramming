import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameLoop implements ActionListener {
    private static final int INTERVAL = 1000 / 60;

    private Game game;
    private Timer timer;

    public GameLoop(Game game) {
        this.game = game;

        timer = new Timer(INTERVAL, this);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        game.getGamePanel().repaint();
    }
}

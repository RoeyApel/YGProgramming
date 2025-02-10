import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameLoop implements ActionListener {
    private static final int INTERVAL = 1000 / 60;
    private Timer looper;
    private Controller controller;

    public GameLoop(Controller controller) {
        this.controller = controller;
        looper = new Timer(INTERVAL, this);
    }

    public void start() {
        looper.start();
    }

    public void stop() {
        looper.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.update();
        controller.getCustomPanel().repaint();
    }
}

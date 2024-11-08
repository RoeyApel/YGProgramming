import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.NumericShaper.Range;
import java.util.Random;

import javax.swing.*;

public class PipeSpawner implements ActionListener {
    private static final int DELAY_MILLIS = 1500;

    private Random random = new Random();

    private Timer spawnTimer;
    private FlappyBird flappyBird;

    public PipeSpawner(FlappyBird flappyBird) {
        spawnTimer = new Timer(DELAY_MILLIS, this);
        this.flappyBird = flappyBird;
    }

    public void startSpawner() {
        spawnTimer.start();
    }

    public void stopSpawner() {
        spawnTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spawnPipe();
    }

    private void spawnPipe() {
        int startY = getRndPositionY();

        Pipe topPipe = new Pipe(Images.TOP_PIPE, startY);
        flappyBird.addPipe(topPipe);

        Pipe buttomPipe = new Pipe(Images.BUTTON_PIPE, startY + topPipe.height + Pipe.GAP_SIZE);
        flappyBird.addPipe(buttomPipe);
    }

    private int getRndPositionY() {
        int min = -Images.TOP_PIPE.getHeight(), max = 0;
        return getRndNumber(min, max);
    }

    private int getRndNumber(int min, int max) {
        return random.nextInt(min, max + 1);
    }
}

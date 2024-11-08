import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JPanel;

public class FlappyBird extends JPanel implements KeyListener {
    public static final float GRAVITY = 1.2f;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private PipeSpawner pipeSpawner;
    private GameLoop gameLoop;
    private boolean gameOver;
    private Score score;

    public FlappyBird() {
        this.setPreferredSize(new Dimension(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);

        gameLoop = new GameLoop(this);

        bird = new Bird();

        score = new Score();

        pipes = new ArrayList<>();
        pipeSpawner = new PipeSpawner(this);

        gameLoop.startLoop();
        pipeSpawner.startSpawner();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(Images.BACKGROUND.getImage(), 0, 0,
                Images.BACKGROUND.getWidth(), Images.BACKGROUND.getHeight(), null);

        bird.draw(g);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        
        score.draw(g, gameOver);
    }

    public void update() {
        bird.update();

        for (Pipe pipe : pipes) {
            pipe.update();

            if (bird.collidingWith(pipe)) {
                gameOver = true;
            }

            if (!pipe.passed && pipe.hasPassed(bird)) {
                pipe.passed = true;
                score.increment();
            }
        }

        if (bird.y > GameFrame.GAME_HEIGHT) {
            gameOver = true;
        }
        if (gameOver) {
            stopGame();
        }
    }

    public void stopGame() {
        pipeSpawner.stopSpawner();
        gameLoop.stopLoop();
    }

    public void addPipe(Pipe pipe) {
        pipes.add(pipe);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

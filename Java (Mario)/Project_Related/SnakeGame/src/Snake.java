import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    private ArrayList<SnakePart> snake;

    public Snake() {
        initSnake();
    }

    private void initSnake() {
        snake = new ArrayList<>();

        SnakePart part = new SnakePart();
        snake.add(part);
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
        addSnakePart();
    }

    public void changeDirection(int direction) {
        if (direction == snake.get(0).getDirection() || direction == getReverseDir(snake.get(0).getDirection())) {
            return;
        }
        int delay = 0;
        snake.get(0).setDirection(direction);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < snake.size(); i++) {
                    try {
                        Thread.sleep(1000 / 6);
                    } catch (InterruptedException e) {
                    }
                    snake.get(i).setDirection(direction);
                }
            }
        }).start();
    }

    private int getReverseDir(int direction) {
        int output = direction == UP ? DOWN
                : direction == DOWN ? UP
                        : direction == LEFT ? RIGHT
                                : direction == RIGHT ? LEFT
                                        : 99;
        return output;
    }

    public void addSnakePart() {
        SnakePart part = new SnakePart();
        part.setInitialMovementDelay(snake.get(snake.size() - 1).getInitialMovementDelay() + SnakePart.NOMRAL_DELAY);
        snake.add(part);
    }

    public void drawSnake(Graphics g, int dx, int dy) {
        for (SnakePart snakePart : snake) {
            snakePart.draw(g, dx, dy);
        }
    }

    public void update() {
        for (SnakePart snakePart : snake) {
            snakePart.update();
        }
    }
}

import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {

    public static final int MOVEMENT_COOLDOWN = 5;

    private ArrayList<SnakePart> snake;
    private int tick;

    public Snake() {
        tick = 0;
        initSnake();
    }

    private void initSnake() {
        snake = new ArrayList<>();

        createSnakeHead();
        addSnakePart();
        addSnakePart();

    }

    public void drawSnake(Graphics g, int dx, int dy) {
        for (SnakePart snakePart : snake) {
            snakePart.draw(g, dx, dy);
        }
    }

    public void update() {
        if (tick == MOVEMENT_COOLDOWN) {
            snake.get(0).move();

            for (int i = 1; i < snake.size(); i++) {
                if (snake.get(i).getInitialCooldown() == 0) {
                    snake.get(i).setDirection(snake.get(i - 1).getPreviousDirection());
                    snake.get(i).move();
                    snake.get(i - 1).setPreviousDirection(snake.get(i - 1).getDirection());
                } else {
                    snake.get(i).decInitialCooldown();
                }
            }
            tick = 0;
        } else {
            tick++;
        }
    }

    public boolean collidsWithItself() {
        SnakePart head = snake.get(0);
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).getInitialCooldown() == 0 && head.isInEqualPosition(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean outOfBounds() {
        return snake.get(0).getCol() < 0 || snake.get(0).getCol() > Game.SIZE
                || snake.get(0).getRow() < 0 || snake.get(0).getRow() > Game.SIZE;
    }

    public void changeDirection(Directions direction) {
        snake.get(0).setDirection(direction);
    }

    public void createSnakeHead() {
        SnakePart part = new SnakePart(0, Game.SIZE / 2, Game.SIZE / 2, Directions.DOWN, 0);
        snake.add(part);
    }

    public void addSnakePart() {
        SnakePart lastSnakePart = snake.get(snake.size() - 1);
        SnakePart part = new SnakePart(lastSnakePart.getId(), lastSnakePart.getRow(),
                lastSnakePart.getCol(),
                lastSnakePart.getPreviousDirection(), lastSnakePart.getInitialCooldown() + 1);
        snake.add(part);

    }

    // public void changeDirection(int direction) {
    // if (direction == snake.get(0).getDirection() || direction ==
    // getReverseDir(snake.get(0).getDirection())) {
    // return;
    // }
    // int delay = 0;
    // snake.get(0).setDirection(direction);

    // new Thread(new Runnable() {
    // @Override
    // public void run() {
    // for (int i = 1; i < snake.size(); i++) {
    // try {
    // Thread.sleep((1000 / 60) * SnakePart.MOVEMENT_COOLDOWN);
    // } catch (InterruptedException e) {
    // }
    // snake.get(i).setDirection(direction);
    // }
    // }
    // }).start();
    // }

    // private int getReverseDir(int direction) {
    // int output = direction == UP ? DOWN
    // : direction == DOWN ? UP
    // : direction == LEFT ? RIGHT
    // : direction == RIGHT ? LEFT
    // : 99;
    // return output;
    // }
}

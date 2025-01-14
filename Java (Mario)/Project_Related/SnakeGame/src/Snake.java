import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {

    public static final int MOVEMENT_COOLDOWN = 3;
    public static final int NUM_OF_PARTS_START = 4;

    private ArrayList<SnakePart> snake;
    private int tick;

    public Snake() {
        tick = 0;
        initSnake();
    }

    private void initSnake() {
        snake = new ArrayList<>();

        createSnakeHead();

        for (int i = 0; i < NUM_OF_PARTS_START; i++) {
            addSnakePart();
        }
    }

    public void drawSnake(Graphics g, int dx, int dy) {
        for (SnakePart snakePart : snake) {
            snakePart.draw(g, dx, dy);
        }
    }

    public void update() {
        if (tick == MOVEMENT_COOLDOWN) {
            moveSnake();
            tick = 0;
        } else {
            tick++;
        }
    }

    private synchronized void moveSnake() {
        getSnakeHead().move();

        int i;
        for (i = 1; i < snake.size(); i++) {
            snake.get(i).setDirection(snake.get(i - 1).getPreviousDirection());

            if (snake.get(i).getInitialCooldown() == 0) {
                snake.get(i).move();
            } else {
                snake.get(i).decInitialCooldown();
            }

            snake.get(i - 1).setPreviousDirection(snake.get(i - 1).getDirection());
        }
        snake.get(i - 1).setPreviousDirection(snake.get(i - 1).getDirection());
    }

    public boolean collidsWithItself() {
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).getInitialCooldown() == 0 && getSnakeHead().isInEqualPosition(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean collidsWithApple(Apple apple) {
        return getSnakeHead().getCol() == apple.getCol() && getSnakeHead().getRow() == apple.getRow();
    }

    public boolean outOfBounds() {
        return snake.get(0).getCol() < 0 || snake.get(0).getCol() > Game.SIZE - 1
                || snake.get(0).getRow() < 0 || snake.get(0).getRow() > Game.SIZE - 1;
    }

    public synchronized void changeDirection(Directions direction) {
        Directions currentDirection = getSnakeHead().getDirection();

        if (isValidDirection(currentDirection, direction)) {
            getSnakeHead().setDirection(direction);
        }
    }

    private boolean isValidDirection(Directions currentDirection, Directions nextDirection) {
        if (currentDirection == nextDirection) {
            return false;
        }
        if (currentDirection == Directions.DOWN && nextDirection == Directions.UP) {
            return false;
        }
        if (currentDirection == Directions.UP && nextDirection == Directions.DOWN) {
            return false;
        }
        if (currentDirection == Directions.LEFT && nextDirection == Directions.RIGHT) {
            return false;
        }
        if (currentDirection == Directions.RIGHT && nextDirection == Directions.LEFT) {
            return false;
        }
        return true;
    }

    public void createSnakeHead() {
        SnakePart part = new SnakePart(0, Game.SIZE / 2, Game.SIZE / 2, Directions.DOWN, 0);
        snake.add(part);
    }

    public synchronized void addSnakePart() {
        SnakePart lastSnakePart = snake.get(snake.size() - 1);
        SnakePart part = new SnakePart(lastSnakePart.getId() + 1, lastSnakePart.getRow(),
                lastSnakePart.getCol(),
                lastSnakePart.getDirection(), lastSnakePart.getInitialCooldown() + 1);
        snake.add(part);

    }

    public synchronized boolean isSnakeAt(int col, int row) {
        for (SnakePart snakePart : snake) {
            if (snakePart.getCol() == col && snakePart.getRow() == row)
                return true;
        }
        return false;
    }

    public boolean tooCloseToHead(int col, int row, int minDis) {
        SnakePart head = getSnakeHead();

        return col > head.getCol() - minDis && col < head.getCol() + minDis && row > head.getRow() - minDis
                && row < head.getRow() + minDis;
    }

    public SnakePart getSnakeHead() {
        return snake.get(0);
    }

    public int getSize() {
        return snake.size();
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

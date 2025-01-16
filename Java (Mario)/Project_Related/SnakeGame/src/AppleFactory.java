import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class AppleFactory {
    public static final int SPAWN_INTERVAL = 50;
    private Random rnd = new Random();
    private ArrayList<Apple> apples;
    private ArrayList<Apple> applesToBeRemoved;
    private Game game;
    private Snake snake;

    public AppleFactory(Game game) {
        this.game = game;
        this.snake = game.getSnake();
        apples = new ArrayList<>();
        applesToBeRemoved = new ArrayList<>();
    }

    public synchronized void drawApples(Graphics g, int dx, int dy) {
        for (Apple apple : apples) {
            apple.draw(g, dx, dy);
        }
    }

    public synchronized void update() {
        for (Apple apple : apples) {
            if (snake.collidsWithApple(apple)) {
                applesToBeRemoved.add(apple);
                snake.addSnakePart();
                game.increaseScore(Game.POINTS_PER_APPLE);
            }
        }

        for (Apple apple : applesToBeRemoved) {
            apples.remove(apple);
        }
    }

    public void spawnApple() {
        Point sPosition = findSpawnSpot();
        Apple apple = new Apple(sPosition.x, sPosition.y);

        apples.add(apple);
    }

    private Point findSpawnSpot() {
        Point position = getRndPosition();

        int minDis = 5;

        while (snake.tooCloseToHead(position.x, position.y, minDis)
                || spotOcuppied(position.x, position.y)) {
            position = getRndPosition();
        }
        return position;
    }

    private synchronized boolean spotOcuppied(int col, int row) {
        if (snake.isSnakeAt(col, row))
            return true;

        for (Apple apple : apples) {
            if (apple.getCol() == col && apple.getRow() == row)
                return true;
        }
        return false;
    }

    private Point getRndPosition() {
        int col = rnd.nextInt(Game.SIZE);
        int row = rnd.nextInt(Game.SIZE);
        return new Point(col, row);
    }

    public int getNumOfApples() {
        return apples.size();
    }

}

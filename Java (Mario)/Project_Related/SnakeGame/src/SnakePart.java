import java.awt.Color;
import java.awt.Graphics;
import java.sql.Time;
import java.util.Timer;

import javax.swing.*;

public class SnakePart {
    public static final int NOMRAL_DELAY = 10;

    private int row, col;
    private int x, y;
    private int width, height;
    private Color color;
    private int direction;
    private int tick;
    private int initialMovementDelay;

    public SnakePart() {
        color = new Color(30, 105, 200);
        direction = Snake.DOWN;
        tick = 0;
        row = Game.SIZE / 2;
        col = Game.SIZE / 2;
        initialMovementDelay = 0;
    }

    public void draw(Graphics g, int dx, int dy) {
        x = col * dx;
        y = row * dy;
        width = dx;
        height = dy;

        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(new Color(30, 30, 200));
        g.drawRect(x, y, width, height);
    }

    public void update() {
        if (tick == NOMRAL_DELAY + initialMovementDelay) {
            move();
            tick = 0;
            initialMovementDelay = 0;
        } else {
            tick++;
        }
    }

    private void move() {
        switch (direction) {
            case Snake.UP:
                row--;
                break;
            case Snake.DOWN:
                row++;
                break;
            case Snake.LEFT:
                col--;
                break;
            case Snake.RIGHT:
                col++;
                break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getInitialMovementDelay() {
        return initialMovementDelay;
    }

    public void setInitialMovementDelay(int delay) {
        initialMovementDelay = delay;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

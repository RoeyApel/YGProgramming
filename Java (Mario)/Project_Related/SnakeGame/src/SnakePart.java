import java.awt.Color;
import java.awt.Graphics;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import javax.swing.*;

public class SnakePart {

    private int row, col;
    private int x, y;
    private int width, height;
    private Color color;
    private int direction, previousDirection;

    public SnakePart(int row, int col) {
        color = new Color(30, 105, 200);
        direction = Snake.DOWN;
        previousDirection = Snake.DOWN;
        this.row = row;
        this.col = col;
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

    public void move() {
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

    public void setDirection(int newDirection) {
        this.previousDirection = this.direction;
        this.direction = newDirection;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPreviousDirection() {
        return previousDirection;
    }

    public void setPreviousDirection(int previousDirection) {
        this.previousDirection = previousDirection;
    }

}

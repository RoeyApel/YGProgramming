import java.awt.Color;
import java.awt.Graphics;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import javax.swing.*;

public class SnakePart {

    private int id;
    private int row, col;
    private int x, y;
    private int width, height;
    private Color color;
    private Directions direction, previousDirection;
    private int initialCooldown;

    public SnakePart(int id, int row, int col, Directions direction, int initialCooldown) {
        color = new Color(30, 105, 200);
        this.direction = direction;
        previousDirection = direction;
        this.row = row;
        this.col = col;
        this.id = id;
        this.initialCooldown = initialCooldown;
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

        g.setColor(Color.black);
        // g.drawString(id + "", x + width / 2, y + height / 2);
        // g.drawString("(" + col + "," + row + ")", x + width / 2, y + height / 2);
    }

    public void move() {
        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
        }
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions newDirection) {
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

    public Directions getPreviousDirection() {
        return previousDirection;
    }

    public void setPreviousDirection(Directions previousDirection) {
        this.previousDirection = previousDirection;
    }

    public int getInitialCooldown() {
        return initialCooldown;
    }

    public void setInitialCooldown(int initialCooldown) {
        this.initialCooldown = initialCooldown;
    }

    public void decInitialCooldown() {
        initialCooldown--;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

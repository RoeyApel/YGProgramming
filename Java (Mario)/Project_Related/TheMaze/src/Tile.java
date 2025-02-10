import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tile {
    public static final int TILE_WIDTH = CustomPanel.WIDTH / Maze.COLS;
    public static final int TILE_HEIGHT = CustomPanel.HEIGHT / Maze.ROWS;
    private static final int WALL_SIZE = (TILE_HEIGHT + TILE_WIDTH) / 15;
    private static final int WALL_OFFSET = WALL_SIZE / 2;

    public static final int NORMAL = 0;
    public static final int START = 1;
    public static final int TARGET = 2;
    public static final int USER_TARGET = 3;

    public static final String WALL_COLOR = "#36454F";
    public static final String TILE_COLOR = "#D3D3D3";
    public static final String PATH_COLOR = "#BEBEBE";
    public static final String TARGET_COLOR = "#B22222";
    public static final String START_COLOR = "#22B222";
    public static final String USER_TARGET_COLOR = "#FFFFE0";

    int row, col;
    String colorTxt;
    int status;

    public Tile(int row, int col, String colorTxt, int status) {
        this.row = row;
        this.col = col;
        this.colorTxt = colorTxt;
        this.status = status;
    }

    public void setColor(String colorTxt) {
        this.colorTxt = colorTxt;
    }

    public void draw(Graphics g) {
        int x = col * TILE_WIDTH;
        int y = row * TILE_HEIGHT;

        g.setColor(Color.decode(colorTxt));
        g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);

        if (status == TARGET) {
            g.setColor(Color.decode(TARGET_COLOR));
            g.fillRect(x + TILE_WIDTH / 4, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
        } else if (status == START) {
            g.setColor(Color.decode(START_COLOR));
            g.fillRect(x + TILE_WIDTH / 4, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
        } else if (status == USER_TARGET) {
            g.setColor(Color.decode(USER_TARGET_COLOR));
            g.fillRect(x + TILE_WIDTH / 4, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
        }
    }

    public void drawWalls(Graphics g, ArrayList<Integer> walls) {
        int newRow, newCol, dirX, dirY;

        for (Integer neighbor : walls) {
            newRow = neighbor / Maze.COLS;
            newCol = neighbor % Maze.COLS;

            dirX = newCol - col;
            dirY = newRow - row;
            drawWall(g, row, col, dirX, dirY);
        }
    }

    private void drawWall(Graphics g, int row, int col, int dirX, int dirY) {
        int x = 0, y = 0, width = 0, height = 0;

        if (dirX == 1 && dirY == 0) {
            // Right wall
            x = (col + 1) * TILE_WIDTH - WALL_SIZE + WALL_OFFSET;
            y = row * TILE_HEIGHT;
            width = WALL_SIZE;
            height = TILE_HEIGHT;
        } else if (dirX == 0 && dirY == 1) {
            // Down wall
            x = col * TILE_WIDTH;
            y = (row + 1) * TILE_HEIGHT - WALL_SIZE + WALL_OFFSET;
            width = TILE_WIDTH;
            height = WALL_SIZE;
        } else if (dirX == 0 && dirY == -1) {
            // Up wall
            x = col * TILE_WIDTH;
            y = row * TILE_HEIGHT - WALL_OFFSET;
            width = TILE_WIDTH;
            height = WALL_SIZE;
        } else {
            // Left wall
            x = col * TILE_WIDTH - WALL_OFFSET;
            y = row * TILE_HEIGHT;
            width = WALL_SIZE;
            height = TILE_HEIGHT;
        }
        g.setColor(Color.decode(WALL_COLOR));
        g.fillRect(x, y, width, height);
    }

    public void toggle() {
        colorTxt = colorTxt == PATH_COLOR ? TILE_COLOR : PATH_COLOR;
    }

    public boolean equals(int row, int col) {
        return this.row == row && this.col == col;
    }

    public void clear() {
        this.colorTxt = TILE_COLOR;
    }

    public void mark() {
        this.colorTxt = PATH_COLOR;
    }

    public void setGuide(boolean guide) {
        if (status == NORMAL && guide) {
            status = USER_TARGET;
        } else if (status == USER_TARGET) {
            status = NORMAL;
        }
    }
}

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

public class Maze {
    private static final int ROWS = 36;
    private static final int COLS = 36;
    private static final int CELL_WIDTH = CustomPanel.WIDTH / COLS;
    private static final int CELL_HEIGHT = CustomPanel.HEIGHT / ROWS;
    private static final int WALL_SIZE = (CELL_HEIGHT + CELL_WIDTH) / 15;
    private static final int WALL_OFFSET = WALL_SIZE / 2;
    private static final int START_ROW = 0;
    private static final int START_COL = 0;
    private static final int GEN_INTERVAL = 20;
    private static final String WALL_COLOR = "#36454F";
    private static final String TILE_COLOR = "#D3D3D3";

    private final int[][] directions = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    private HashMap<Integer, ArrayList<Integer>> maze = new HashMap<>();
    private Controller controller;

    public Maze(Controller controller) {
        this.controller = controller;
        initMaze();
    }

    private void initMaze() {
        int cellNum;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cellNum = i * COLS + j;
                ArrayList<Integer> neighbors = new ArrayList<>(4);
                maze.put(cellNum, neighbors);
            }
        }
    }

    public void drawMaze(Graphics g) {
        int x, y;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                x = j * CELL_WIDTH;
                y = i * CELL_HEIGHT;

                g.setColor(Color.decode(TILE_COLOR));
                g.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                drawWalls(g, i, j);
            }
        }
    }

    private void drawWalls(Graphics g, int row, int col) {
        ArrayList<Integer> neighbors = maze.get(row * COLS + col);
        ArrayList<Integer> potentialNeighbors = getNeighbors(row * COLS + col);

        potentialNeighbors.removeAll(neighbors);

        int newRow, newCol, dirX, dirY;

        for (Integer neighbor : potentialNeighbors) {
            newRow = neighbor / COLS;
            newCol = neighbor % COLS;

            dirX = newCol - col;
            dirY = newRow - row;
            drawWall(g, row, col, dirX, dirY);
        }
    }

    private void drawWall(Graphics g, int row, int col, int dirX, int dirY) {
        int x = 0, y = 0, width = 0, height = 0;

        if (dirX == 1 && dirY == 0) {
            // Right wall
            x = (col + 1) * CELL_WIDTH - WALL_SIZE + WALL_OFFSET;
            y = row * CELL_HEIGHT;
            width = WALL_SIZE;
            height = CELL_HEIGHT;
        } else if (dirX == 0 && dirY == 1) {
            // Down wall
            x = col * CELL_WIDTH;
            y = (row + 1) * CELL_HEIGHT - WALL_SIZE + WALL_OFFSET;
            width = CELL_WIDTH;
            height = WALL_SIZE;
        } else if (dirX == 0 && dirY == -1) {
            // Up wall
            x = col * CELL_WIDTH;
            y = row * CELL_HEIGHT - WALL_OFFSET;
            width = CELL_WIDTH;
            height = WALL_SIZE;
        } else {
            // Left wall
            x = col * CELL_WIDTH - WALL_OFFSET;
            y = row * CELL_HEIGHT;
            width = WALL_SIZE;
            height = CELL_HEIGHT;
        }
        g.setColor(Color.decode(WALL_COLOR));
        g.fillRect(x, y, width, height);
    }

    public void generateMaze() {
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < ROWS * COLS; i++) {
            if (!visited.contains(i)) {
                dfsGeneratePath(visited, i);
            }
        }
    }

    private void dfsGeneratePath(HashSet<Integer> visited, int start) {
        Stack<Integer> stack = new Stack<>();
        int current;

        stack.push(start);

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (visited.contains(current))
                continue;

            visited.add(current);
            ArrayList<Integer> neighbors = getNeighbors(current);

            if (neighbors.isEmpty())
                continue;

            Collections.shuffle(neighbors);

            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    maze.get(current).add(neighbor);
                    maze.get(neighbor).add(current);
                    stack.push(neighbor);
                    break;
                }
            }

            try {
                Thread.sleep(GEN_INTERVAL);
            } catch (InterruptedException e) {
            }
            controller.getCustomPanel().repaint();
        }
    }

    private ArrayList<Integer> getNeighbors(int vertex) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int row = vertex / COLS;
        int col = vertex % COLS;

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isInBounds(newRow, newCol)) {
                neighbors.add(newRow * COLS + newCol);
            }
        }
        return neighbors;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
}

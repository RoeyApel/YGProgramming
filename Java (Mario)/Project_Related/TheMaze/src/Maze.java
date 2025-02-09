import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Maze {
    private static final int ROWS = 30;
    private static final int COLS = 30;
    private static final int CELL_WIDTH = CustomPanel.WIDTH / COLS;
    private static final int CELL_HEIGHT = CustomPanel.HEIGHT / ROWS;

    private static final int WALL_SIZE = (CELL_HEIGHT + CELL_WIDTH) / 15;
    private static final int WALL_OFFSET = WALL_SIZE / 2;

    private static final int GEN_INTERVAL = 10;

    private static final String WALL_COLOR = "#36454F";
    private static final String TILE_COLOR = "#D3D3D3";
    private static final String PATH_COLOR = "#BEBEBE";
    private static final String TARGET_COLOR = "#B22222";
    private static final String START_COLOR = "#22B222";

    private final int[][] directions = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    private HashMap<Integer, ArrayList<Integer>> maze = new HashMap<>();
    private Controller controller;
    boolean mazeDone;
    private int start, target;

    public Maze(Controller controller) {
        this.controller = controller;
        start = 0;
        target = ROWS * COLS - 1;
        initMaze();
    }

    public Maze(Controller controller, int start, int target) {
        this.controller = controller;
        this.start = start;
        this.target = target;
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
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                drawTile(g, i, j, CELL_WIDTH, CELL_HEIGHT, Color.decode(TILE_COLOR));
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                drawWalls(g, i, j);
            }
        }
    }

    private void drawTile(Graphics g, int i, int j, int width, int height, Color tileColor) {
        int x = j * CELL_WIDTH;
        int y = i * CELL_HEIGHT;

        g.setColor(tileColor);
        g.fillRect(x, y, width, height);

        if (i * COLS + j == target) {
            g.setColor(Color.decode(TARGET_COLOR));
            g.fillRect(x + CELL_WIDTH / 4, y + CELL_HEIGHT / 4, CELL_WIDTH / 2, CELL_HEIGHT / 2);
        } else if (i * COLS + j == start) {
            g.setColor(Color.decode(START_COLOR));
            g.fillRect(x + CELL_WIDTH / 4, y + CELL_HEIGHT / 4, CELL_WIDTH / 2, CELL_HEIGHT / 2);
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

    public void drawSolution(Graphics g) {
        ArrayList<Integer> path = getSolution();

        for (Integer tile : path) {
            drawTile(g, tile / COLS, tile % COLS, CELL_WIDTH, CELL_HEIGHT,
                    Color.decode(PATH_COLOR));
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                drawWalls(g, i, j);
            }
        }
    }

    private ArrayList<Integer> getSolution() {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] parents = new int[COLS * ROWS];
        int current = start;

        queue.add(current);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current == target) {
                return reconstructPath(parents, start, target);
            }
            ArrayList<Integer> neighbors = maze.get(current);

            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(current);
                    parents[neighbor] = current;
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> reconstructPath(int[] parents, int start, int target) {
        ArrayList<Integer> path = new ArrayList<>();

        int current = target;
        while (current != start) {
            path.add(current);
            current = parents[current];
        }
        path.add(start);

        return path;
    }

    public void generateMaze() {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.peek();

            visited.add(current);

            int neighbor = getRandomUnvisitedNeighbor(visited, current);

            if (neighbor == -1) {
                stack.pop();
                continue;
            }

            maze.get(current).add(neighbor);
            maze.get(neighbor).add(current);
            stack.push(neighbor);

            // ** A delay to visualies the building of the maze.
            try {
                Thread.sleep(GEN_INTERVAL);
            } catch (InterruptedException e) {
            }
            controller.getCustomPanel().repaint();
        }
        mazeDone = true;
    }

    private int getRandomUnvisitedNeighbor(HashSet<Integer> visited, int vertex) {
        ArrayList<Integer> neighbors = getNeighbors(vertex);

        if (neighbors.isEmpty())
            return -1;

        Collections.shuffle(neighbors);

        for (Integer neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                return neighbor;
            }
        }

        return -1;
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

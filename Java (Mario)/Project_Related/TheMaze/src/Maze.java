import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.Timer;

public class Maze {
    private static final int PLAYER_MOVEMENT_DELAY = 150;
    public static final int ROWS = 9;
    public static final int COLS = 9;

    private static final int GEN_INTERVAL = 10;

    public Tile[][] grid = new Tile[ROWS][COLS];
    private final int[][] directions = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    private HashMap<Integer, ArrayList<Integer>> maze = new HashMap<>();
    private Controller controller;
    boolean mazeDone;
    ArrayList<Integer> solution;
    Player player;
    int start, target, guide;
    Timer timer;

    public Maze(Controller controller) {
        this.controller = controller;
        start = 0;
        target = ROWS * COLS - 1;
        guide = -1;
        initMaze();
        player = new Player(start / COLS, start % COLS);
    }

    public Maze(Controller controller, int start, int target) {
        this.controller = controller;
        this.start = start;
        this.target = target;
        initMaze();
    }

    private void initMaze() {
        int cellNum, status;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cellNum = i * COLS + j;
                ArrayList<Integer> neighbors = new ArrayList<>(4);
                maze.put(cellNum, neighbors);

                status = cellNum == start ? Tile.START : cellNum == target ? Tile.TARGET : Tile.NORMAL;
                grid[i][j] = new Tile(i, j, Tile.TILE_COLOR, status);
            }
        }
    }

    public void drawMaze(Graphics g) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j].draw(g);
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ArrayList<Integer> neighbors = maze.get(i * COLS + j);
                ArrayList<Integer> potentialNeighbors = getNeighbors(i * COLS + j);

                potentialNeighbors.removeAll(neighbors);

                grid[i][j].drawWalls(g, potentialNeighbors);
            }
        }

        player.draw(g);
    }

    public void movePlayer(Directions direction) {
        if (guide != -1) {
            return;
        }
        player.look(direction);

        int newRow = player.row + direction.i;
        int newCol = player.col + direction.j;

        if (!isInBounds(newRow, newCol)) {
            return;
        }

        int currentVertex = player.row * COLS + player.col;
        int newVertex = newRow * COLS + newCol;

        if (hasNeighbor(currentVertex, newVertex))
            player.move(direction);
    }

    private boolean hasNeighbor(int vertex1, int vertex2) {
        for (int neighbor : maze.get(vertex1)) {
            if (neighbor == vertex2) {
                return true;
            }
        }
        return false;
    }

    public void movePlayerToStart() {
        player.look(Directions.DOWN);
        player.row = start / COLS;
        player.col = start % COLS;
    }

    public void guidePlayer(int row, int col) {
        int current = player.row * COLS + player.col;
        guide = row * COLS + col;

        ArrayList<Integer> path = findSolution(current, guide);
        Iterator<Integer> iterator = path.iterator();

        if (!iterator.hasNext())
            return;
        iterator.next();

        timer = new Timer(PLAYER_MOVEMENT_DELAY, new ActionListener() {
            int current = player.row * COLS + player.col;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!iterator.hasNext()) {
                    ((Timer) e.getSource()).stop();
                    cencelGuide();
                    return;
                }

                int vertex = iterator.next();
                Directions direction = findDirection(current, vertex);
                player.look(direction);
                player.move(direction);
                current = vertex;
            }
        });

        timer.start();
    }

    private Directions findDirection(int vertex1, int vertex2) {
        int row1 = vertex1 / COLS;
        int col1 = vertex1 % COLS;
        int row2 = vertex2 / COLS;
        int col2 = vertex2 % COLS;

        if (row2 - row1 == Directions.DOWN.i) {
            return Directions.DOWN;
        } else if (row2 - row1 == Directions.UP.i) {
            return Directions.UP;
        } else if (col2 - col1 == Directions.LEFT.j) {
            return Directions.LEFT;
        } else if (col2 - col1 == Directions.RIGHT.j) {
            return Directions.RIGHT;
        } else {
            return null;
        }
    }

    public void cencelGuide() {
        grid[guide / COLS][guide % COLS].setGuide(false);
        guide = -1;
        timer.stop();
    }

    public void clearTiles() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j].clear();
            }
        }
    }

    public void DisplaySolution() {
        for (Integer tile : solution) {
            grid[tile / COLS][tile % COLS].mark();
        }
    }

    public ArrayList<Integer> findSolution(int start, int target) {
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

        Collections.reverse(path);
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
        }
        mazeDone = true;
        solution = findSolution(start, target);
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

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

}

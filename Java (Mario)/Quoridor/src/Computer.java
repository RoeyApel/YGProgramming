import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Computer {
    private Board board;
    private Character bot, player;
    private ArrayList<Vertex> botShortestPath, playerShortestPath;

    public Computer(Board board) {
        this.board = board;
        this.bot = board.getOpponent();
        this.player = board.getPlayer();
    }

    public Wall findBestBlockingSpot(ArrayList<Vertex> path, int reach, Character character) {
        Wall bestWall = null;
        int maxStepsCount = path.size() - 1;
        for (int i = 1; i < reach + 1 && i < path.size(); i++) {
            Vertex vertex = path.get(i);

            Queue<Wall> walls = board.getPlacableWalls(vertex.row, vertex.col);

            while (!walls.isEmpty()) {
                Wall wall = walls.poll();
                wall.type = Walls.WALL;

                if (!isValidWallPlacement(wall)) {
                    continue;
                }

                board.placeWall(wall);

                ArrayList<Vertex> shortestPath = findShortestPath(character.getRow(), character.getCol(), character.getWinningRow());

                board.removeWall(wall);

                int stepsCount = shortestPath.size() - 1;

                if (stepsCount > maxStepsCount) {
                    maxStepsCount = stepsCount;
                    bestWall = wall;
                }
            }
        }
        return bestWall;
    }

    public boolean isValidWallPlacement(Wall wall) {
        board.placeWall(wall);

        ArrayList<Vertex> playerPath = findShortestPath(player.getRow(), player.getCol(), player.getWinningRow());

        if (playerPath == null) {
            board.removeWall(wall);
            return false;
        }

        ArrayList<Vertex> opponentPath = findShortestPath(bot.getRow(), bot.getCol(), bot.getWinningRow());

        if (opponentPath == null) {
            board.removeWall(wall);
            return false;
        }

        board.removeWall(wall);
        return true;
    }

    public void moveBot(int row, int col) {
        Position currentPosition = bot.getPosition();

        board.get(currentPosition).setOcuppied(false);
        board.get(row, col).setOcuppied(true);

        bot.setPosition(row, col);
    }

    public ArrayList<Vertex> findShortestPath(int row, int col, int winningRow) {
        Vertex current = new Vertex(row, col);
        return aStarFindShortestPath(current, winningRow);
    }

    private ArrayList<Vertex> aStarFindShortestPath(Vertex current, int targetRow) {
        HashSet<Vertex> visited = new HashSet<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>();

        current.g = 0;
        current.h = heuristic(current, targetRow);
        current.f = current.g + current.h;

        queue.add(current);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            if (current.row == targetRow) {
                return recreatePath(current);
            }

            for (Move move : board.getLegalMoves(current)) {
                Vertex neighbor = new Vertex(move.getTarget());

                if (visited.contains(neighbor))
                    continue;

                int tentativeG = current.g + 1;

                if (tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor, targetRow);
                    neighbor.f = neighbor.g + neighbor.h;
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }

    private int heuristic(Vertex current, int targetRow) {
        return targetRow - current.row;
    }

    private ArrayList<Vertex> recreatePath(Vertex current) {
        ArrayList<Vertex> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);

        return path;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Character getBot() {
        return bot;
    }

    public void setBot(Character bot) {
        this.bot = bot;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public ArrayList<Vertex> getBotShortestPath() {
        return botShortestPath;
    }

    public void setBotShortestPath(ArrayList<Vertex> botShortestPath) {
        this.botShortestPath = botShortestPath;
    }

    public ArrayList<Vertex> getPlayerShortestPath() {
        return playerShortestPath;
    }

    public void setPlayerShortestPath(ArrayList<Vertex> playerShortestPath) {
        this.playerShortestPath = playerShortestPath;
    }

    public void updateShortestPaths() {
        botShortestPath = findShortestPath(bot.getRow(), bot.getCol(), bot.getWinningRow());
        playerShortestPath = findShortestPath(player.getRow(), player.getCol(), player.getWinningRow());
    }

}

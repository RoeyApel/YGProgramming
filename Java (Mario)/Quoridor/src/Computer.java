import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Computer {
    private Board board;
    private Character bot, player;

    public Computer(Board board) {
        this.board = board;
        this.bot = board.getOpponent();
        this.player = board.getPlayer();
    }

    public void makeMove() {

    }

    private void moveBot(int row, int col) {
        Position currentPosition = bot.getPosition();

        board.get(currentPosition).setOcuppied(false);
        board.get(row, col).setOcuppied(true);

        bot.setPosition(row, col);
    }

    public ArrayList<Vertex> getShortestPath(int row, int col, int winningRow) {
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
        return path;
    }

}

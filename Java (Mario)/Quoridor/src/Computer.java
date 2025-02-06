import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Computer {
    private Board board;

    public Computer(Board board) {
        this.board = board;
    }

    public ArrayList<Position> getShortestPath(int row, int col, int winningRow) {
        ArrayList<Position> path;
        ArrayList<Position> shortestPath = null;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < Board.COLS; i++) {
            path = bfsFindShortestPath(row, col, winningRow, i);
            if (path != null && path.size() < min) {
                min = path.size();
                shortestPath = path;
            }
        }
        return shortestPath;
    }

    public boolean canReachGoal(int row, int col, int winningRow) {
        for (int i = 0; i < Board.COLS; i++) {
            if (dfsHasPath(row, col, winningRow, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsHasPath(int currentRow, int currentCol, int TargetRow, int TargetCol) {
        HashSet<Position> visited = new HashSet<>();
        Stack<Position> stack = new Stack<>();

        Position currentPosition = new Position(currentRow, currentCol);

        stack.add(currentPosition);

        while (!stack.isEmpty()) {
            currentPosition = stack.pop();

            if (visited.contains(currentPosition)) {
                continue;
            }

            visited.add(currentPosition);

            if (currentPosition.equals(TargetRow, TargetCol)) {
                return true;
            }

            for (Move move : board.getLegalMoves(currentPosition)) {
                Position position = move.getTarget();

                if (!visited.contains(position)) {
                    stack.add(position);
                }
            }
        }
        return false;
    }

    private ArrayList<Position> bfsFindShortestPath(int currentRow, int currentCol, int TargetRow, int TargetCol) {
        HashSet<Position> visited = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        Position[][] parents = new Position[Board.ROWS][Board.COLS];

        Position currentPosition = new Position(currentRow, currentCol);

        queue.add(currentPosition);

        while (!queue.isEmpty()) {
            currentPosition = queue.poll();

            if (visited.contains(currentPosition)) {
                continue;
            }
            visited.add(currentPosition);

            if (currentPosition.equals(TargetRow, TargetCol)) {
                return recreatePath(parents, currentRow, currentCol, TargetRow, TargetCol);
            }

            for (Move move : board.getLegalMoves(currentPosition)) {
                Position position = move.getTarget();

                if (!visited.contains(position)) {
                    parents[position.row][position.col] = currentPosition;
                    queue.add(position);
                }
            }
        }
        return null;
    }

    private ArrayList<Position> recreatePath(Position[][] parents, int startRow, int startCol, int endRow, int endCol) {
        ArrayList<Position> path = new ArrayList<>();
        Position current = new Position(endRow, endCol);

        while (!current.equals(startRow, startCol)) {
            path.add(current);
            current = parents[current.row][current.col];
        }
        path.add(current);
        return path;
    }

}

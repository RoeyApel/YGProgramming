import java.util.ArrayList;

public class DifficultBot extends Bot {

    private static final int REACH = 4;

    public DifficultBot(Computer computer) {
        super(computer);
    }

    public void makeMove() {
        ArrayList<Vertex> playerPath = computer.getPlayerShortestPath();
        ArrayList<Vertex> botPath = computer.getBotShortestPath();

        int playerStepsCount = playerPath.size() - 1;
        int botStepsCount = botPath.size() - 1;

        int botWallsCount = computer.getBot().getWallsCount();

        Vertex next = botPath.get(1);

        if (botWallsCount == 0) {
            computer.moveBot(next.row, next.col);
            return;
        }

        if (botStepsCount > playerStepsCount) {
            Wall wall = computer.findBestBlockingSpot(playerPath, REACH, computer.getPlayer());

            computer.getBoard().placeWall(wall);
            return;
        }

        computer.moveBot(next.row, next.col);
    }

}

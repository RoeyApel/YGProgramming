import java.util.ArrayList;

public class EasyBot extends Bot {
    private static final int REACH = 2;

    public EasyBot(Computer computer) {
        super(computer);
    }

    @Override
    public void makeMove() {
        ArrayList<Vertex> playerPath = computer.getPlayerShortestPath();
        ArrayList<Vertex> botPath = computer.getBotShortestPath();

        int playerSteps = playerPath.size() - 1;
        int botSteps = botPath.size() - 1;
        int botWallCount = computer.getBot().getWallsCount();

        Vertex nextMove = botPath.get(1);

        if (botWallCount == 0) {
            computer.moveBot(nextMove.row, nextMove.col);
            return;
        }

        boolean playerClose = playerSteps <= 3;
        boolean random30Chance = Math.random() < 0.3;
        boolean random70Chance = Math.random() < 0.7;

        if ((playerClose && random70Chance) || random30Chance) {
            Wall wall = computer.findBestBlockingSpot(playerPath, REACH, computer.getPlayer());
            if (wall != null) {
                computer.getBoard().placeWall(wall);
                return;
            }
        }

        computer.moveBot(nextMove.row, nextMove.col);
    }

}

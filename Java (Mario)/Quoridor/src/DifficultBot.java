import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class DifficultBot extends Bot {

    private static final int REACH = 4;

    public DifficultBot(Computer computer) {
        super(computer);
    }

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

        boolean playerClose = playerSteps <= 4;
        boolean botBehind = botSteps > playerSteps;
        boolean randomBlock = botSteps == playerSteps && Math.random() < 0.3;

        if ((playerClose && botBehind) || randomBlock) {
            Wall wall = computer.findBestBlockingSpot(playerPath, REACH, computer.getPlayer());
            if (wall != null) {
                computer.getBoard().placeWall(wall);
                return;
            }
        }

        computer.moveBot(nextMove.row, nextMove.col);

    }

}

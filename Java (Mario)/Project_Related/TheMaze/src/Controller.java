import java.awt.Graphics;

public class Controller {
    CustomFrame customFrame;
    CustomPanel customPanel;
    Maze maze;

    public Controller() {
        maze = new Maze(this);

        initFrame();

        maze.generateMaze();
    }

    private void initFrame() {
        customFrame = new CustomFrame();

        customPanel = new CustomPanel(this);
        customFrame.add(customPanel);

        customFrame.pack();
        customFrame.setVisible(true);
    }

    public void render(Graphics g) {
        maze.drawMaze(g);
    }

    public CustomPanel getCustomPanel() {
        return customPanel;
    }
}

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    CustomFrame customFrame;
    CustomPanel customPanel;
    Maze maze;
    boolean showSolution;

    public Controller() {
        maze = new Maze(this);

        initFrame();

        maze.generateMaze();
    }

    private void initFrame() {
        customFrame = new CustomFrame();

        customPanel = new CustomPanel(this);
        customPanel.addKeyListener(this);
        customPanel.setFocusable(true);
        customPanel.requestFocusInWindow();
        customFrame.add(customPanel);

        customFrame.pack();
        customFrame.setVisible(true);
    }

    public void render(Graphics g) {
        maze.drawMaze(g);
        if (showSolution) {
            maze.drawSolution(g);
        }
    }

    public CustomPanel getCustomPanel() {
        return customPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && maze.mazeDone) {
            showSolution = !showSolution;
            customPanel.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

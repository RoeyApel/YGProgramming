import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Controller implements KeyListener, MouseListener, MouseMotionListener {
    CustomFrame customFrame;
    CustomPanel customPanel;
    Maze maze;
    boolean showSolution;
    boolean pressed;
    Tile currentTile;

    public Controller() {
        maze = new Maze(this);

        initFrame();

        maze.generateMaze();
    }

    private void initFrame() {
        customFrame = new CustomFrame();

        customPanel = new CustomPanel(this);
        customPanel.addKeyListener(this);
        customPanel.addMouseListener(this);
        customPanel.addMouseMotionListener(this);
        customPanel.setFocusable(true);
        customPanel.requestFocusInWindow();
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && maze.mazeDone) {
            maze.clearTiles();

            if (!showSolution) {
                maze.DisplaySolution();
            }
            showSolution = !showSolution;
            customPanel.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            maze.clearTiles();
            customPanel.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;

        int row = e.getY() / Tile.TILE_HEIGHT;
        int col = e.getX() / Tile.TILE_WIDTH;

        currentTile = maze.grid[row][col];

        currentTile.toggle();
        customPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!pressed) {
            return;
        }
        int row = e.getY() / Tile.TILE_HEIGHT;
        int col = e.getX() / Tile.TILE_WIDTH;

        if (currentTile.equals(row, col)) {
            return;
        }

        currentTile = maze.grid[row][col];
        currentTile.toggle();
        customPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

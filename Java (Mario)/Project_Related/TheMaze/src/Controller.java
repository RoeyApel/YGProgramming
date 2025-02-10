import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements KeyListener, MouseListener, MouseMotionListener {

    private static final int TOGGLE = 0;
    private static final int WRITE = 1;
    private static final int ERASE = 2;
    private static final int GUIDE = 3;
    CustomFrame customFrame;
    CustomPanel customPanel;
    Maze maze;
    boolean showSolution;
    boolean pressed;
    Tile currentTile;
    GameLoop gameLoop;
    int mode = TOGGLE;

    public Controller() {
        maze = new Maze(this);

        initFrame();
        gameLoop = new GameLoop(this);
        gameLoop.start();

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

    public void update() {
    }

    public CustomPanel getCustomPanel() {
        return customPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_R && maze.mazeDone) {
            maze.clearTiles();

            if (!showSolution) {
                maze.DisplaySolution();
            }
            showSolution = !showSolution;
        } else if (key == KeyEvent.VK_V) {
            maze.movePlayerToStart();
        } else if (key == KeyEvent.VK_C) {
            maze.clearTiles();
        } else if (key == KeyEvent.VK_F) {
            mode = WRITE;
        } else if (key == KeyEvent.VK_E) {
            mode = ERASE;
        } else if (key == KeyEvent.VK_T) {
            mode = TOGGLE;
        } else if (key == KeyEvent.VK_G && maze.mazeDone) {
            mode = GUIDE;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            maze.movePlayer(Directions.LEFT);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            maze.movePlayer(Directions.RIGHT);
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            maze.movePlayer(Directions.UP);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            maze.movePlayer(Directions.DOWN);
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

        if (!maze.isInBounds(row, col)) {
            return;
        }
        currentTile = maze.grid[row][col];

        if (mode == TOGGLE) {
            currentTile.toggle();
        } else if (mode == WRITE) {
            currentTile.mark();
        } else if (mode == ERASE) {
            currentTile.clear();
        } else if (mode == GUIDE) {
            if (maze.guide == row * Maze.COLS + col) {
                maze.cencelGuide();
                return;
            }
            if (maze.guide != -1) {
                maze.cencelGuide();
            }
            currentTile.setGuide(true);
            System.out.println("dfedf");
            maze.guidePlayer(row, col);
        }
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

        if (currentTile.equals(row, col) || !maze.isInBounds(row, col)) {
            return;
        }

        currentTile = maze.grid[row][col];

        if (mode == TOGGLE) {
            currentTile.toggle();
        } else if (mode == WRITE) {
            currentTile.mark();
        } else if (mode == ERASE) {
            currentTile.clear();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}

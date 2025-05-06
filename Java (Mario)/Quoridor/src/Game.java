import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.smartcardio.Card;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game implements MouseListener, KeyListener {
    private static final String PLAYING = "playing";
    private static final String MENU = "menu";

    private GameFrame gameFrame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private Board board;
    private Character currentPlayer;
    private Computer computer;
    private Bot bot;
    private Queue<Wall> wallsOptions = new LinkedList<>();
    private boolean wallSelectionActive, moveSelectionActive;
    private Position lastSlotClicked = new Position(-1, -1);
    private boolean gameOver;
    private int turns;
    private ArrayList<Vertex> shortestPath;
    private boolean debugMode = false;
    private Modes mode;
    private boolean botTurn;

    public Game() {
        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.addKeyListener(this);
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(this);

        mainPanel.add(menuPanel, MENU);
        mainPanel.add(gamePanel, PLAYING);

        menuPanel.pvpButton.addActionListener(e -> startGame(Modes.PVP));
        menuPanel.easyButton.addActionListener(e -> startGame(Modes.EASY));
        menuPanel.hardButton.addActionListener(e -> startGame(Modes.HARD));

        gameFrame.add(mainPanel);

        cardLayout.show(mainPanel, MENU);

        gameFrame.setVisible(true);
    }

    private void startGame(Modes mode) {
        this.mode = mode;

        gameOver = false;
        botTurn = false;
        board = new Board();
        currentPlayer = board.getPlayer();
        computer = new Computer(board);

        if (mode == Modes.EASY) {
            bot = new EasyBot(computer);
            turns = 0;
            computer.updateShortestPaths();
        }
        else if (mode == Modes.HARD) {
            bot = new DifficultBot(computer);
            turns = 0;
            computer.updateShortestPaths();
        }

        cardLayout.show(mainPanel, PLAYING);
    }

    public void returnToMenu() {
        cardLayout.show(mainPanel, MENU);
    }

    public void render(Graphics g, int panelWidth, int panelHeight) {
        board.drawBoard(g, panelWidth, panelHeight);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameOver || botTurn)
            return;

        int row = e.getY() / board.getSlotHeight();
        int col = e.getX() / board.getSlotWidth();

        if (currentPlayer.isAt(row, col)) {
            onClickCurrentPlayer(row, col);
        }
        else if (board.isMarked(row, col)) {
            onClickMarkedSlot(row, col);
        }
        else if (SwingUtilities.isLeftMouseButton(e)) {
            onLeftClickSlot(row, col);
        }
        else if (SwingUtilities.isRightMouseButton(e) && wallSelectionActive) {
            onRightClickSlot(row, col);
        }

        gamePanel.repaint();
    }

    private void nextTurn() {
        if (currentPlayer.hasWon()) {
            gameOver = true;
            showGameOverDialog();
            returnToMenu();
            return;
        }

        turns++;
        currentPlayer = (turns % 2 == 0) ? board.getPlayer() : board.getOpponent();

        turnReset();

        if (isBotTurn()) {
            computer.updateShortestPaths();
            botTurn = true;
            scheduleBotMove();
        }
    }

    private void scheduleBotMove() {
        int delayMs = 300;
        Timer t = new Timer(delayMs, e -> {
            ((Timer) e.getSource()).stop();
            onBotMove();
            gamePanel.repaint();
        });
        t.setRepeats(false);
        t.start();
    }

    private void onPlayerMove(int row, int col) {
        moveCurrentPlayer(row, col);
        nextTurn();
    }

    private void onBotMove() {
        bot.makeMove();
        botTurn = false;
        nextTurn();
    }

    private boolean isBotTurn() {
        return mode != Modes.PVP && currentPlayer == board.getOpponent();
    }

    private void showGameOverDialog() {
        String winner = (currentPlayer == board.getPlayer()) ? "You" : "Opponent";
        JOptionPane.showMessageDialog(gameFrame, "The winner is: " + winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onRightClickSlot(int row, int col) {
        Wall wall = wallsOptions.poll();
        wall.type = Walls.WALL;

        if (!computer.isValidWallPlacement(wall)) {
            JOptionPane.showMessageDialog(gameFrame, "please don't place a wall that gets you or your opponent completely stuck",
                    "Invaid Wall Placement", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        board.placeWall(wall);

        currentPlayer.decWallsCount();

        nextTurn();
    }

    private void onLeftClickSlot(int row, int col) {
        if (moveSelectionActive) {
            deactivateMoveSelection(row, col);
        }

        if (lastSlotClicked.equals(row, col)) {
            displayNextWallOption(row, col);
        }
        else {
            if (wallSelectionActive) {
                deactivateWallSelection(row, col);
            }

            activateWallSelection(row, col);
        }
        lastSlotClicked.setPosition(row, col);
    }

    private boolean activateWallSelection(int row, int col) {
        wallsOptions = board.getPlacableWalls(row, col);

        if (wallsOptions.isEmpty()) {
            return false;
        }

        board.placeWall(wallsOptions.peek());
        wallSelectionActive = true;
        return true;
    }

    private boolean displayNextWallOption(int row, int col) {
        if (wallsOptions.isEmpty())
            return false;

        board.removeWall(wallsOptions.peek());

        wallsOptions.add(wallsOptions.poll());

        board.placeWall(wallsOptions.peek());
        return true;
    }

    private void onClickMarkedSlot(int row, int col) {
        deactivateMoveSelection(row, col);

        onPlayerMove(row, col);
    }

    private void turnReset() {
        wallSelectionActive = false;
        moveSelectionActive = false;
        currentPlayer.setMoves(null);
        wallsOptions = null;
        lastSlotClicked.col = -1;
        lastSlotClicked.row = -1;
    }

    private void moveCurrentPlayer(int row, int col) {
        Position currentPosition = currentPlayer.getPosition();

        board.get(currentPosition.row, currentPosition.col).setOcuppied(false);
        board.get(row, col).setOcuppied(true);

        currentPlayer.setPosition(row, col);
    }

    private void onClickCurrentPlayer(int row, int col) {
        if (wallSelectionActive) {
            deactivateWallSelection(row, col);
        }

        if (moveSelectionActive) {
            deactivateMoveSelection(row, col);
        }
        else {
            activateMoveSelection(row, col);
        }
        lastSlotClicked.setPosition(row, col);
    }

    private void activateMoveSelection(int row, int col) {
        ArrayList<Move> moves = board.getLegalMoves(row, col);
        currentPlayer.setMoves(moves);
        board.markSlots(moves);
        moveSelectionActive = true;
    }

    private void deactivateMoveSelection(int row, int col) {
        ArrayList<Move> moves = currentPlayer.getMoves();
        board.unmarkSlots(moves);
        currentPlayer.setMoves(null);
        moveSelectionActive = false;
    }

    private void deactivateWallSelection(int row, int col) {
        Wall wall = wallsOptions.peek();
        board.removeWall(wall);
        wallsOptions = null;
        wallSelectionActive = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            returnToMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private enum Modes {
        PVP, EASY, HARD;
    }
}

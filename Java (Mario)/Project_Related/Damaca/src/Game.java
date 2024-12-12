import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Game extends JPanel implements MouseListener {
    public Game() {
        this.setPreferredSize(new Dimension(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT));
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Board board = new Board();
        board.drawBoard(g);
        board.drawPieces(g);
        g.drawImage(Pieces.OPPONENT_KING.getImage(), 330, 330, null);
    }

    public void update() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
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
}

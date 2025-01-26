import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Character {
    private Rectangle hitbox;
    private Position position;
    private Image image;
    private ArrayList<Move> moves;
    private int wallsCount;

    public Character(Image image, int row, int col) {
        this.image = image;
        position = new Position(row, col);
        wallsCount = 0;

        hitbox = new Rectangle();
    }

    public void draw(Graphics g) {
        g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
    }

    public void setBounds(int x, int y, int width, int height) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
    }

    public boolean isAt(int row, int col) {
        return position.row == row && position.col == col;
    }

    public boolean isAt(Position otherPos) {
        return position.row == otherPos.row && position.col == otherPos.col;
    }

    public void setXYPos(int x, int y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getRow() {
        return position.row;
    }

    public void setRow(int row) {
        position.row = row;
    }

    public int getCol() {
        return position.col;
    }

    public void setCol(int col) {
        position.col = col;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int row, int col) {
        position.row = row;
        position.col = col;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public int getWallsCount() {
        return wallsCount;
    }

    public void setWallsCount(int wallsCount) {
        this.wallsCount = wallsCount;
    }

    public void decWallsCount() {
        wallsCount--;
    }
}

import java.awt.*;

public class Slot {
    public static final int NONE = -1;
    public static final int EMPTY = 0;
    public static final int WALL = 1;

    private Rectangle hitbox;
    private Position position;
    private boolean ocuppied;
    private Slots kind;
    private int rightWall;
    private int bottomWall;
    private Image image;

    public Slot(Slots kind, int row, int col) {
        this.kind = kind;
        ocuppied = false;
        position = new Position(row, col);

        bottomWall = (row == Board.ROWS - 1) ? NONE : EMPTY;
        rightWall = (col == Board.COLS - 1) ? NONE : EMPTY;

        hitbox = new Rectangle();
        image = kind.getImg();
    }

    public void draw(Graphics g) {
        g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);

        g.setColor(Color.black);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        // g.drawString(slotId + "", hitbox.x + hitbox.width / 2, hitbox.y +
        // hitbox.height / 2);
    }

    public void mark() {
        image = kind.getMarkedImg();
    }

    public void unmark() {
        image = kind.getImg();
    }

    public void setBounds(int x, int y, int width, int height) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
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

    public int getRightWall() {
        return rightWall;
    }

    public void setRightWall(int leftWall) {
        this.rightWall = leftWall;
    }

    public int getBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(int buttomWall) {
        this.bottomWall = buttomWall;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isOcuppied() {
        return ocuppied;
    }

    public void setOcuppied(boolean hasCharacter) {
        this.ocuppied = hasCharacter;
    }

    public boolean isMarked() {
        return image == kind.getMarkedImg();
    }

}

import java.awt.*;

public class Slot {
    private static final int WALL_OFFSET = 3;

    private Rectangle hitbox;
    private Position position;
    private boolean ocuppied;
    private Slots kind;
    private Walls rightWall;
    private Walls bottomWall;
    private Image image;

    public Slot(Slots kind, int row, int col) {
        this.kind = kind;
        ocuppied = false;
        position = new Position(row, col);

        bottomWall = (row == Board.ROWS - 1) ? Walls.NONE : Walls.EMPTY;
        rightWall = (col == Board.COLS - 1) ? Walls.NONE : Walls.EMPTY;

        hitbox = new Rectangle();
        image = kind.getImg();
    }

    public void draw(Graphics g) {
        g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);

        drawBorder(g);

        // g.drawString(position.row * Board.COLS + position.col + "", hitbox.x +
        // hitbox.width / 2, hitbox.y +
        // hitbox.height / 2);
    }

    public void drawWalls(Graphics g) {
        if (rightWall != Walls.NONE && rightWall != Walls.EMPTY) {
            g.drawImage(rightWall.getVerticalImg(), hitbox.x + hitbox.width - WALL_OFFSET, hitbox.y,
                    hitbox.width, hitbox.height, null);
        }
        if (bottomWall != Walls.NONE && bottomWall != Walls.EMPTY) {
            g.drawImage(bottomWall.getHorizontalImg(), hitbox.x, hitbox.y + WALL_OFFSET, hitbox.width,
                    hitbox.height, null);
        }
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
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

    public Walls getRightWall() {
        return rightWall;
    }

    public void setRightWall(Walls rightWall) {
        this.rightWall = rightWall;
    }

    public Walls getBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(Walls bottomWall) {
        this.bottomWall = bottomWall;
    }

}

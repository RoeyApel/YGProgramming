import java.awt.*;

public class Slot {
    private static final int WALL_OFFSET = 3;

    private Rectangle hitbox;
    private Position position;
    private boolean ocuppied;
    private Slots type;
    private Walls rightWall;
    private Walls bottomWall;
    private Image image;

    public Slot(Slots kind, int row, int col) {
        this.type = kind;
        ocuppied = false;
        position = new Position(row, col);

        bottomWall = Walls.EMPTY;
        rightWall = Walls.EMPTY;

        hitbox = new Rectangle();
        image = kind.getImg();
    }

    public void draw(Graphics g) {
        g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);

        drawBorder(g);

    }

    public void drawWalls(Graphics g) {
        if (rightWall != Walls.EMPTY) {
            g.drawImage(rightWall.getVerticalImg(), hitbox.x + hitbox.width - WALL_OFFSET, hitbox.y, hitbox.width, hitbox.height, null);
        }
        if (bottomWall != Walls.EMPTY) {
            g.drawImage(bottomWall.getHorizontalImg(), hitbox.x, hitbox.y + WALL_OFFSET, hitbox.width, hitbox.height, null);
        }
    }

    private void drawBorder(Graphics g) {
        g.setColor(new Color(85, 93, 80));
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public void mark() {
        image = type.getMarkedImg();
    }

    public void unmark() {
        image = type.getImg();
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
        return image == type.getMarkedImg();
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

    public boolean hasBottomWall() {
        return bottomWall == Walls.WALL;
    }

    public boolean hasRightWall() {
        return rightWall == Walls.WALL;
    }

}

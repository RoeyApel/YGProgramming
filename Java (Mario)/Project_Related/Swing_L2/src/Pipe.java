import java.awt.*;

public class Pipe {
    private static final float MOVE_FORCE = -7;
    public static final int GAP_SIZE = 150;
    int x, y;
    int width, height;
    float velocity;
    Image image;
    boolean passed;

    public Pipe(Images pipe, int y) {
        x = GameFrame.GAME_WIDTH;
        this.y = y;
        width = pipe.getWidth();
        height = pipe.getHeight();
        velocity = MOVE_FORCE;
        image = pipe.getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void update() {
        x += velocity;
    }

    public boolean hasPassed(Bird bird) {
        return bird.x > this.x + this.width;
    }
}

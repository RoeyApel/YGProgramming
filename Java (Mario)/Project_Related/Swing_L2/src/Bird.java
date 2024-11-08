import java.awt.Graphics;
import java.awt.Image;

public class Bird {
    private static final float JUMP_FORCE = -22;
    int x, y;
    int width, height;
    float velocity, acceleration;
    Image image;

    public Bird() {
        x = GameFrame.GAME_WIDTH / 8;
        y = GameFrame.GAME_HEIGHT / 2;
        width = Images.BIRD.getWidth();
        height = Images.BIRD.getHeight();
        image = Images.BIRD.getImage();
        velocity = 0;
        acceleration = 0;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void update() {
        addForce(FlappyBird.GRAVITY);

        velocity += acceleration;
        velocity = limit(velocity, 13);

        y += velocity;
        y = Math.max(y, 0);

        acceleration = 0;
    }

    public boolean collidingWith(Pipe pipe) {
        return this.x < pipe.x + pipe.width &&
                this.x + this.width > pipe.x &&
                this.y < pipe.y + pipe.height &&
                this.y + this.height > pipe.y;
    }

    private void addForce(float force) {
        acceleration += force;
    }

    private float limit(float v, float limit) {
        if (v >= limit) {
            return limit;
        }
        if (v <= -limit) {
            return -limit;
        }
        return v;
    }

    public void jump() {
        addForce(JUMP_FORCE);
    }

}

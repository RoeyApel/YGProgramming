import java.awt.Graphics;

public class Wall extends Piece {
    private boolean built;

    public Wall(int id, boolean built) {
        this.id = id;
        this.built = built;
    }

    @Override
    public void draw(Graphics g) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }

}

import java.awt.Graphics;

public class Menu implements GameState {

    private Game game;
    private Mouse mouse;

    public Menu(Game game) {
        this.game = game;
        this.mouse = game.getMouse();
    }

    @Override
    public void draw(Graphics g) {
        game.drawScore(g, "center");
    }

    @Override
    public void update() {

    }

}

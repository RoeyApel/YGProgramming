import java.awt.Graphics;
import java.awt.Image;

public class Player {
    int row, col;
    int width, height;
    Image image;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        this.width = Tile.TILE_WIDTH / 2;
        this.height = Tile.TILE_HEIGHT / 2;
        this.image = Characters.PLAYER.sprite_down;
    }

    public void draw(Graphics g) {
        int x = Tile.TILE_WIDTH / 4 + col * Tile.TILE_WIDTH;
        int y = Tile.TILE_HEIGHT / 4 + row * Tile.TILE_HEIGHT;

        g.drawImage(image, x, y, width, height, null);
    }

    public void move(Directions direction) {
        row = row + direction.i;
        col = col + direction.j;
    }

    public void look(Directions direction) {
        image = Characters.PLAYER.getImage(direction);
    }

}

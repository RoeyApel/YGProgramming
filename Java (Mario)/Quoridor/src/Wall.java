public class Wall {
    // public static final int NONE = -1;
    // public static final int EMPTY = 0;
    // public static final int WALL = 1;
    // public static final int SELECTED_WALL = 2;

    public Walls type;
    public Position position;
    public Directions direction;

    public Wall(Walls type, Position position, Directions direction) {
        this.type = type;
        this.position = position;
        this.direction = direction;
    }

    public Wall(int row, int col, Directions direction) {
        this.direction = direction;
        position = new Position(row, col);
    }

}

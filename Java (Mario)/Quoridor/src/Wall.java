public class Wall {

    public Walls type;
    public Position position;
    public Directions direction;

    public Wall(Walls type, Position position, Directions direction) {
        this.type = type;
        this.position = position;
        this.direction = direction;
    }

    public Wall(Walls type, int row, int col, Directions direction) {
        this.type = type;
        this.direction = direction;
        position = new Position(row, col);
    }

}

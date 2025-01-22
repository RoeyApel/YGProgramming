public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position() {
        row = col = 0;
    }

    public boolean equals(Position other) {
        return this.row == other.row && this.col == other.col;
    }

    public boolean equals(int row, int col) {
        return this.row == row && this.col == col;
    }
}

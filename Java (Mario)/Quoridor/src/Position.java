import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Position))
            return false;
        Position pos = (Position) o;
        return row == pos.row && col == pos.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public boolean equals(Position other) {
        return this.row == other.row && this.col == other.col;
    }

    public boolean equals(int row, int col) {
        return this.row == row && this.col == col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

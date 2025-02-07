import java.util.Objects;

public class Vertex extends Position implements Comparable<Vertex> {
    public Vertex parent;
    public int f, g, h;

    public Vertex(int row, int col) {
        super(row, col);
        g = Integer.MAX_VALUE;
    }

    public Vertex(Position position) {
        super(position.row, position.col);
        g = Integer.MAX_VALUE;
    }

    public Vertex() {
        g = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(this.f, other.f);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vertex))
            return false;
        Vertex vertex = (Vertex) other;
        return row == vertex.row && col == vertex.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

}

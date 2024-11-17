public class SSquare extends SShape {
    int width;

    public SSquare() {
        super("Square");
    }

    public SSquare(int width) {
        super("Square");
        this.width = width;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "), width: " + width;
    }

    @Override
    public int getArea() {
        throw new UnsupportedOperationException("Unimplemented method 'getArea'");
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}

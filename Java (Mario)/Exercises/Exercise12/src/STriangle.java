public class STriangle extends SShape {

    static final int LEFT = -1;
    static final int RIGHT = -1;
    static final int CENTER = -1;
    int width;
    int mode;

    public STriangle() {
        super("Triangle");
    }

    public STriangle(int width, int mode) {
        super("Triangle");
        this.width = width;
    }

    @Override
    public int getArea() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArea'");
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

}

public abstract class SShape {
    String type;
    int x, y;

    public SShape(String type) {
        this.type = type;
        x = 0;
        y = 0;
    }

    void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public abstract int getArea();

    public abstract void show();
}

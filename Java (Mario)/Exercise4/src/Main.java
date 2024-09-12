import T1.Triangle;

public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(6);
        Triangle triangle2 = new Triangle(6);
        Triangle triangle3 = new Triangle(6);
        Triangle triangle4 = new Triangle(6);
        triangle.show('r','y');
        System.err.println(Triangle.getCount());
    }
}

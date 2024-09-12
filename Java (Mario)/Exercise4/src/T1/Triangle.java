package T1;

public class Triangle {
    private static int count;
    private int size;

    public Triangle() {
        count++;
    }

    public Triangle(int size) {
        this.size = size;
        count++;
    }
    public static int getCount(){
        return count;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int area() {
        return (size * size) / 2;
    }

    public double hypot() {
        return Math.sqrt(size * size + size * size);
    }

    public void show() {
        System.out.printf("Area: %d, Size: %d", area(), size);
    }

    public void show(char type) {
        if (type == 'l') {
            printTriangleLeft('*');
        } else if (type == 'r') {
            printTriangleRight('*');
        }
    }

    public void show(char type, char c) {
        if (type == 'l') {
            printTriangleLeft(c);
        } else if (type == 'r') {
            printTriangleRight(c);
        }
    }

    private void printTriangleLeft(char c) {
        String triangle = "";
        String line = "";
        for (int i = 0; i < size; i++) {
            line = line + c;
            triangle = triangle + line + "\n";
        }
        System.out.println(triangle);
    }

    private void printTriangleRight(char c) {
        String triangle = "";
        String line = "";
        String space = "";

        for (int i = 0; i < size; i++) {
            space = space + " ";
        }

        for (int i = 0; i < size; i++) {
            line = line + c;
            space = space.replaceFirst(" ", "");
            triangle = triangle + space + line + "\n";
        }
        System.out.println(triangle);

    }

}

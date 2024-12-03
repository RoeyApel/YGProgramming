public class Mathing {

    public int addPos(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("can't add negative numbers");
        }
        return a + b;
    }
}

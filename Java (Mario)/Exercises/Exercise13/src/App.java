public class App {
    public static void main(String[] args) throws Exception {
        Mathing mathing = new Mathing();

        try {
            mathing.addPos(-1, 0);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}

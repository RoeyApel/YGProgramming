public class c9 {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 256; i++) {
            System.out.printf(" %03d ", i);
            if (i % 16 == 0) {
                System.out.println("");
            }
        }
    }
}

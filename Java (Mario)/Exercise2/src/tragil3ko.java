import java.util.Scanner;

public class tragil3ko {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter yout number here: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= (n - 2); i++) {
            for (int  ; j + i < (n - 2); j++) {
                int c = n - i - j;
                if (c > 0) {
                    if (c != i && c != j && i != j) {
                        int x = i;
                        int y = j;
                        if (y > x) {
                            x = y + x;
                            y = x - y;
                            x = x - y;
                        }
                        if (c > x) {
                            x = c + x;
                            c = x - c;
                            x = x - c;
                        }
                        if (y > c) {
                            c = y + c;
                            y = x - c;
                            c = x - c;
                        }
                        System.out.printf("the three numbers: %d = %d + %d + %d\n", n, y, c, x);
                    }
                }
            }
        }
        scanner.close();

    }
}
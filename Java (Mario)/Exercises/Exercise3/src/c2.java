import java.util.Scanner;

public class c2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        printAll3NumAddOps(num);
    }

    private static void printAll3NumAddOps(int num) {
        for (int i = 1; i < num - 2; i++) {
            for (int j = 1; j + i < num; j++) {
                System.out.printf("%d = %d + %d + %d\n", num, i, j, num - i - j);
            }
        }
    }
}

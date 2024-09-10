import java.util.Scanner;

public class c1 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        printAll2NumAddOps(num);
    }

    private static void printAll2NumAddOps(int num) {
        for (int i = 1; i < num; i++) {
            System.out.printf("%d = %d + %d\n", num, num - i, i);
        }
    }
}

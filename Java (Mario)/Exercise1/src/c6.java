import java.util.Scanner;

public class c6 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("num1: ");
        int num1 = scanner.nextInt();

        System.out.print("num2: ");
        int num2 = scanner.nextInt();
        scanner.close();
        printBiggestCommonDivider(num1, num2);
    }

    private static void printBiggestCommonDivider(int num1, int num2) {
        if (num1 == num2) {
            System.out.println(num1);
            return;
        }
        int big = (num1 > num2) ? num1 : num2, small = (num1 < num2) ? num1 : num2;

        for (int i = small; i > 1; i--) {
            if (big % i == 0 && small % i == 0) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("No Divider");
    }
}

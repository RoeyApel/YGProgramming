import java.util.Scanner;

public class c5 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num = getIntInput(scanner);

        printG(num);
    }

    private static void printG(int num) {
        if (num % 2 == 0) {
            while (num % 2 == 0) {
                num /= 2;
                System.out.print("2 ");
            }
        }
        for (int i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                    System.out.print(i + " ");
                }
            }
        }
        if (num != 1) {
            System.out.println("Prime");
        }
    }

    public static int getIntInput(Scanner scanner) {
        System.out.print("Number: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Number: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}

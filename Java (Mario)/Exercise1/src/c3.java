import java.util.Scanner;

public class c3 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num = getIntInput(scanner);

        printDigits(num);
    }

    private static void printDigits(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        System.out.println("Number of digits: " + count);
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

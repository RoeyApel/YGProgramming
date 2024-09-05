import java.util.Scanner;

public class c4 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num = getIntInput(scanner);

        System.out.println(isPrime(num) ? "Prime" : "Not Prime");
    }

    private static boolean isPrime(int num) {
        if (num % 2 == 0 && num != 2) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
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
